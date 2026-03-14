package hongleap.account_service.application.dto.account;

import lombok.Builder;
import java.math.BigDecimal;
import java.util.UUID;

@Builder
public record AccountResponse(
        UUID accountId,
        String accountTypeCode,
        String accountHolder,
        UUID branchId,
        BigDecimal balance,
        String status
) {}