package hongleap.account_service.application.listener;

import hongleap.account_service.application.adapter.CustomerClient;
import hongleap.account_service.application.dto.customer.CustomerResponse;
import hongleap.account_service.application.mapper.AccountMapper;
import hongleap.account_service.data.entity.AccountEntity;
import hongleap.account_service.data.entity.AccountTypeEntity;
import hongleap.account_service.data.entity.BranchEntity;
import hongleap.account_service.data.entity.CustomerEntity;
import hongleap.account_service.data.repository.AccountRepository;
import hongleap.account_service.data.repository.AccountTypeRepository;
import hongleap.account_service.data.repository.BranchRepository;
import hongleap.account_service.data.repository.CustomerRepository;
import hongleap.common.domain.event.AccountCreatedEvent;
import hongleap.common.domain.valueObject.AccountStatus;
import hongleap.account_service.domain.valueObject.CustomerName;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.time.ZonedDateTime;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
@ProcessingGroup("account-group")
public class AccountListener {

    private final AccountRepository accountRepository;
    private final AccountMapper  accountApplicationMapper;
    private final BranchRepository branchRepository;
    private final AccountTypeRepository  accountTypeRepository;
    private final CustomerRepository customerRepository;
    private final CustomerClient  customerClient;

    @EventHandler
    public void on (AccountCreatedEvent accountCreatedEvent) {
        log.info("AccountCreatedEvent: {}",accountCreatedEvent);

        // save customer snapshot if not exists
        UUID customerId = accountCreatedEvent.customerId().value();
        if (!customerRepository.existsById(customerId)) {
            CustomerResponse customer = customerClient
                    .getCustomerById(customerId.toString())        // UUID → String
                    .orElseThrow(() -> new IllegalStateException(  // Optional → CustomerResponse
                            "Customer not found: " + customerId
                    ));

            CustomerEntity customerEntity = new CustomerEntity();
            customerEntity.setCustomerId(customerId);
            customerEntity.setCustomerName(
                    new CustomerName(
                            customer.name().familyName(),
                            customer.name().givenName()
                    )
            );
            customerEntity.setPhoneNumber(customer.phoneNumber());
            customerRepository.save(customerEntity);
        }

        // we map customerCreatedEvent to customerEntity
        AccountEntity accountEntity = accountApplicationMapper.accountCreatedEventToEntity(accountCreatedEvent);
        // Set these after mapping
        accountEntity.setStatus(AccountStatus.ACTIVE);
        accountEntity.setCreatedAt(ZonedDateTime.now());
        accountEntity.setCreatedBy("system");
        accountEntity.setUpdatedAt(ZonedDateTime.now());
        accountEntity.setUpdatedBy("system");
        accountEntity.setCustomerId(customerId);

//        BranchEntity branchEntity = branchRepository
//                .findById(accountCreatedEvent.branchId().value())
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Branch Not Found"));
//        accountEntity.setBranchId(branchEntity.getId());

        // Set branch only if branchId is present
        UUID branchId = (accountCreatedEvent.branchId() != null)
                ? accountCreatedEvent.branchId().value()
                : null;

        if (branchId != null) {
            BranchEntity branchEntity = branchRepository
                    .findById(branchId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Branch Not Found"));
            accountEntity.setBranchId(branchEntity.getId());
        } else {
            accountEntity.setBranchId(null);
        }

        // Link account type by code, not by accountId
        AccountTypeEntity accountType = accountTypeRepository
                .findByAccountTypeCode(accountCreatedEvent.accountTypeCode()) // 👈 fix this
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account Type Not Found"));
        accountEntity.setAccountType(accountType); // set it

        accountRepository.save(accountEntity);
    }

}
