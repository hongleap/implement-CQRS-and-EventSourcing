package hongleap.account_service.domain.command;

import hongleap.common.domain.valueObject.AccountId;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public record FreezeAccountCommand(
        @TargetAggregateIdentifier
        AccountId accountId,
        String remark,
        String requestedBy
) {

}