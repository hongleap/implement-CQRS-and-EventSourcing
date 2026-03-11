package hongleap.account_service.application.dto.create.response;

import hongleap.common.domain.valueObject.Money;
import lombok.Builder;

@Builder
public record DepositMoneyResponse(
        Money amount,
        String remark
) {}