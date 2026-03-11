package hongleap.account_service.application.dto.create.response;

import hongleap.common.domain.valueObject.AccountId;
import lombok.Builder;

@Builder
public record FrozenResponse(
        AccountId accountId,
        String remark
) {
}
