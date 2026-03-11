package hongleap.account_service.application.dto.create.request;

import hongleap.common.domain.valueObject.AccountId;
import hongleap.common.domain.valueObject.TransactionId;
import hongleap.common.domain.valueObject.Money;
import lombok.Builder;

@Builder
public record WithdrawMoneyRequest(

        AccountId accountId,
        TransactionId transactionId,
        Money amount,
        String remark

) {
}
