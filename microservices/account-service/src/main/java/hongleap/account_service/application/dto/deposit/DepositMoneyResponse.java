package hongleap.account_service.application.dto.deposit;

import hongleap.common.domain.valueObject.Money;
import lombok.Builder;

@Builder
public record DepositMoneyResponse(
        Money amount,
        String remark
) {}