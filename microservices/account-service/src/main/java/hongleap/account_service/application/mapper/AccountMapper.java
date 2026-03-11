package hongleap.account_service.application.mapper;

import hongleap.account_service.data.entity.AccountEntity;

import hongleap.account_service.application.dto.create.request.CreateAccountRequest;
import hongleap.account_service.domain.command.DepositMoneyCommand;
import hongleap.account_service.domain.command.WithdrawMoneyCommand;
import hongleap.account_service.domain.command.CreateAccountCommand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    CreateAccountCommand createAccountRequestToCreateAccountCommand
            (hongleap.common.domain.valueObject.AccountId accountId, CreateAccountRequest createAccountRequest);

    @Mapping(source = "accountId", target = "accountId")
    @Mapping(source = "customerId", target = "customerId")
    @Mapping(source = "branchId", target = "branchId")
    AccountEntity accountCreatedEventToEntity(hongleap.common.domain.event.AccountCreatedEvent event);

    // Map AccountId -> UUID
    default UUID map(hongleap.common.domain.valueObject.AccountId accountId) {
        return accountId != null ? accountId.value() : null;
    }

    // Map CustomerId -> UUID
    default UUID map(hongleap.common.domain.valueObject.CustomerId customerId) {
        return customerId != null ? customerId.value() : null;
    }

    // Map BranchId -> UUID
    default UUID map(hongleap.common.domain.valueObject.BranchId branchId) {
        return branchId != null ? branchId.value() : null;
    }

    @Mapping(target = "accountId", source = "accountId")
    @Mapping(target = "transactionId", source = "transactionId")
    @Mapping(target = "amount", source = "amount")
    @Mapping(target = "remark", source = "remark")
    WithdrawMoneyCommand toWithdrawMoneyCommand(hongleap.common.domain.valueObject.AccountId accountId, hongleap.common.domain.valueObject.TransactionId transactionId, hongleap.common.domain.valueObject.Money amount, String remark);

    @Mapping(target = "accountId", source = "accountId")
    @Mapping(target = "transactionId", source = "transactionId")
    @Mapping(target = "amount", source = "amount")
    @Mapping(target = "remark", source = "remark")
    DepositMoneyCommand toDepositMoneyCommand(hongleap.common.domain.valueObject.AccountId accountId, hongleap.common.domain.valueObject.TransactionId transactionId, hongleap.common.domain.valueObject.Money amount, String remark);

}
