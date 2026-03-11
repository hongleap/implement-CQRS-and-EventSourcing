package hongleap.account_service.application.dto.create.response;

import hongleap.common.domain.valueObject.AccountTypeCode;
import hongleap.common.domain.valueObject.Money;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.util.UUID;

@Builder
public record CreateAccountResponse(
        @NotBlank
        UUID accountId,
        String accountHolder,
        AccountTypeCode accountTypeCode,
        Money initialBalance,
        @NotBlank
        String message
) {
}
