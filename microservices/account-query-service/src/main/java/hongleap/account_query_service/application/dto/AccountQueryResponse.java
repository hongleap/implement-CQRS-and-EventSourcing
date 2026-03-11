package hongleap.account_query_service.application.dto;

import java.util.UUID;

public record AccountQueryResponse(
        UUID accountId,
        String accountNumber
) {
}
