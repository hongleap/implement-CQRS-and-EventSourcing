package hongleap.account_service.data.init;

import hongleap.account_service.data.entity.AccountTypeEntity;
import hongleap.account_service.data.repository.AccountTypeRepository;
import hongleap.common.domain.valueObject.AccountTypeCode;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class AccountTypeInit {


    private final AccountTypeRepository accountTypeRepository;
    @PostConstruct
    public void initCustomerSegment(){
        // Seed account types
        if (accountTypeRepository.count() == 0) {
            AccountTypeEntity saving = new AccountTypeEntity();
            saving.setAccountTypeId(UUID.randomUUID());
            saving.setAccountTypeCode(AccountTypeCode.SAVING);
            accountTypeRepository.save(saving);

            AccountTypeEntity loan = new AccountTypeEntity();
            loan.setAccountTypeId(UUID.randomUUID());
            loan.setAccountTypeCode(AccountTypeCode.LAON);
            accountTypeRepository.save(loan);

            AccountTypeEntity checking = new AccountTypeEntity();
            checking.setAccountTypeId(UUID.randomUUID());
            checking.setAccountTypeCode(AccountTypeCode.CHECKING);
            accountTypeRepository.save(checking);

            AccountTypeEntity payroll = new AccountTypeEntity();
            payroll.setAccountTypeId(UUID.randomUUID());
            payroll.setAccountTypeCode(AccountTypeCode.PAYROLL);
            accountTypeRepository.save(payroll);

            log.info("Account types seeded");
        }
    }
}
