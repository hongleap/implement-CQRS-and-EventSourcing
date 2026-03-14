package hongleap.account_service.application.dto.account;

import lombok.Builder;
import java.util.List;

@Builder
public record AccountListResponse(
        List<AccountResponse> accounts,
        int total
) {}
