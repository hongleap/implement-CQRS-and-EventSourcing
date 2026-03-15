package hongleap.account_service.domain.event;

import hongleap.common.domain.valueObject.CustomerId;
import hongleap.common.domain.valueObject.AccountId;
import hongleap.common.domain.valueObject.AccountStatus;
import lombok.Builder;

import java.time.ZonedDateTime;

@Builder
public record AccountFrozenEvent(
        AccountId accountId,
        CustomerId customerId,
        AccountStatus previousStatus,
        AccountStatus newStatus,
        String reason,
        String requestedBy,
        ZonedDateTime createdAt
) {
}