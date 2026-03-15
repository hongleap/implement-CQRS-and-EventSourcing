package hongleap.account_service.domain.event;

import hongleap.common.domain.valueObject.CustomerId;
import hongleap.common.domain.valueObject.TransactionId;
import lombok.Builder;
import hongleap.common.domain.valueObject.AccountId;
import hongleap.common.domain.valueObject.Money;

import java.time.ZonedDateTime;

@Builder
public record MoneyWithdrawnEvent(
        AccountId accountId,
        CustomerId customerId,
        TransactionId transactionId,
        Money amount,
        Money newBalance,
        String remark,
        ZonedDateTime createdAt
) {}
