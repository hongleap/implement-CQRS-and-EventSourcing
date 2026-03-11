package hongleap.account_service.domain.command;

import hongleap.common.domain.valueObject.Money;
import hongleap.common.domain.valueObject.AccountId;
import hongleap.common.domain.valueObject.TransactionId;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public record DepositMoneyCommand(
        @TargetAggregateIdentifier
        AccountId accountId,
        TransactionId transactionId,
        Money amount,
        String remark
) {
}
