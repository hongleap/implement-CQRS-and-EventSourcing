package hongleap.customer_service.domain.valueobject;

import java.util.UUID;

public record Kyc(
        UUID KycId,
        String type,
        String number
){
}
