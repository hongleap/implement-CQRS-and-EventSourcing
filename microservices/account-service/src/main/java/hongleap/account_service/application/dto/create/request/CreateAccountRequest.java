package hongleap.account_service.application.dto.create.request;

import hongleap.common.domain.valueObject.BranchId;
import hongleap.common.domain.valueObject.CustomerId;
import hongleap.common.domain.valueObject.AccountTypeCode;
import hongleap.common.domain.valueObject.Money;
import jakarta.validation.constraints.NotNull;

public record CreateAccountRequest(

        CustomerId customerId,
        @NotNull
        String accountNumber,
        String accountHolder,
        AccountTypeCode accountTypeCode,
        BranchId branchId,
        Money initialBalance
) {
}
