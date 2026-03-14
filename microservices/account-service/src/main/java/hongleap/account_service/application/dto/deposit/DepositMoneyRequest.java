package hongleap.account_service.application.dto.deposit;


import hongleap.common.domain.valueObject.AccountId;
import hongleap.common.domain.valueObject.TransactionId;
import hongleap.common.domain.valueObject.Money;

public record DepositMoneyRequest(
        AccountId accountId,
        TransactionId transactionId,
        Money amount,
        String remark
) {
}
