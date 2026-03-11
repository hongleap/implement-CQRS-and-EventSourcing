package hongleap.account_service.application.dto.create.response;

import hongleap.account_service.domain.valueObject.CustomerName;

import java.util.UUID;

public record CustomerResponse(
        UUID customerId,
        CustomerName name,
        String phoneNumber
) {
}
