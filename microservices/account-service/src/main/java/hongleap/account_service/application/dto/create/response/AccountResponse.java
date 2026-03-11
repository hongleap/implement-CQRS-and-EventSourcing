package hongleap.account_service.application.dto.create.response;

import lombok.Builder;
import java.math.BigDecimal;
import java.util.UUID;

@Builder
public record AccountResponse(
        UUID accountId,
        String accountTypeCode,
        String accountHolder,
        BigDecimal balance,
        String status
) {}