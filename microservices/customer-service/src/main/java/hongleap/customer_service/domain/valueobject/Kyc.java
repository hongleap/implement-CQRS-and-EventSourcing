package hongleap.customer_service.domain.valueobject;

import java.util.UUID;

public record Kyc(
        UUID customerId,
        String type,
        String number
){
}
