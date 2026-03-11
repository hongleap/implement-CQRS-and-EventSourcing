package hongleap.account_service.domain.command;

import hongleap.common.domain.valueObject.AccountStatus;
import hongleap.common.domain.valueObject.BranchId;
import hongleap.common.domain.valueObject.CustomerId;
import hongleap.common.domain.valueObject.AccountId;
import hongleap.common.domain.valueObject.AccountTypeCode;
import hongleap.common.domain.valueObject.Money;
import lombok.Builder;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Builder
public record CreateAccountCommand(
        @TargetAggregateIdentifier
        AccountId accountId,
        String accountNumber,
        String accountHolder,
        CustomerId customerId,
        AccountTypeCode accountTypeCode,
        BranchId branchId,
        Money initialBalance,
        AccountStatus accountStatus

) {
}
