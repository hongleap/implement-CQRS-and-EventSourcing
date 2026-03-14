package hongleap.account_service.application.dto.withdraw;

import hongleap.common.domain.valueObject.Money;
import lombok.Builder;

@Builder
public record WithdrawMoneyResponse(
        Money amount,
        String remark
) {}