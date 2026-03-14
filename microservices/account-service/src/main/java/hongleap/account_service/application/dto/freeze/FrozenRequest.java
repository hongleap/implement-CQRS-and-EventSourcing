package hongleap.account_service.application.dto.freeze;

import hongleap.common.domain.valueObject.AccountId;

public record FrozenRequest(
        AccountId accountId,
        String remark,
        String requestedBy
) {
}