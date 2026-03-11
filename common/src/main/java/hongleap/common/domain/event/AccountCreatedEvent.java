package hongleap.common.domain.event;

import hongleap.common.domain.valueObject.*;
import lombok.Builder;

import java.time.ZonedDateTime;

@Builder
public record AccountCreatedEvent(

        AccountId accountId,
        String accountNumber,
        String accountHolder,

        CustomerId customerId,
        AccountTypeCode accountTypeCode,
        BranchId branchId,
        Money initialBalance,

        ZonedDateTime createdAt,
        String createdBy,

        AccountStatus status

) {
}

