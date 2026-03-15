package hongleap.account_service.application.mapper;

import hongleap.account_service.data.entity.AccountEntity;
import hongleap.common.domain.event.AccountCreatedEvent;
import hongleap.common.domain.valueObject.AccountId;
import hongleap.common.domain.valueObject.CustomerId;
import hongleap.common.domain.valueObject.BranchId;
import hongleap.common.domain.valueObject.TransactionId;
import hongleap.common.domain.valueObject.Money;
import hongleap.account_service.application.dto.create.CreateAccountRequest;
import hongleap.account_service.domain.command.DepositMoneyCommand;
import hongleap.account_service.domain.command.WithdrawMoneyCommand;
import hongleap.account_service.domain.command.CreateAccountCommand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    CreateAccountCommand createAccountRequestToCreateAccountCommand
            (AccountId accountId, CreateAccountRequest createAccountRequest);

    @Mapping(source = "accountId", target = "accountId")
    @Mapping(source = "customerId", target = "customerId")
    @Mapping(source = "branchId", target = "branchId")
    AccountEntity accountCreatedEventToEntity(AccountCreatedEvent event);

    // Map AccountId -> UUID
    default UUID map(AccountId accountId) {
        return accountId != null ? accountId.value() : null;
    }

    // Map CustomerId -> UUID
    default UUID map(CustomerId customerId) {
        return customerId != null ? customerId.value() : null;
    }

    // Map BranchId -> UUID
    default UUID map(BranchId branchId) {
        return branchId != null ? branchId.value() : null;
    }

    @Mapping(target = "accountId", source = "accountId")
    @Mapping(target = "transactionId", source = "transactionId")
    @Mapping(target = "amount", source = "amount")
    @Mapping(target = "remark", source = "remark")
    WithdrawMoneyCommand toWithdrawMoneyCommand(AccountId accountId, TransactionId transactionId, Money amount, String remark);

    @Mapping(target = "accountId", source = "accountId")
    @Mapping(target = "transactionId", source = "transactionId")
    @Mapping(target = "amount", source = "amount")
    @Mapping(target = "remark", source = "remark")
    DepositMoneyCommand toDepositMoneyCommand(AccountId accountId, TransactionId transactionId, Money amount, String remark);

}
