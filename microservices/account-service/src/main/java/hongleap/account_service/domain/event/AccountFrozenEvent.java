package hongleap.account_service.domain.event;

import hongleap.common.domain.valueObject.AccountStatus;
import lombok.Builder;

import java.time.ZonedDateTime;

@Builder
public record AccountFrozenEvent(
        hongleap.common.domain.valueObject.AccountId accountId,
        hongleap.common.domain.valueObject.CustomerId customerId,
        AccountStatus previousStatus,
        AccountStatus newStatus,
        String reason,
        String requestedBy,
        ZonedDateTime createdAt
) {
}