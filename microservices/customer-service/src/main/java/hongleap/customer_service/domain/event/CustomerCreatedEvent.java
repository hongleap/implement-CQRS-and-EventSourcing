package hongleap.customer_service.domain.event;

import hongleap.common.domain.valueObject.CustomerId;
import hongleap.common.domain.valueObject.CustomerSegmentId;
import hongleap.customer_service.domain.valueobject.*;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record CustomerCreatedEvent(
        CustomerId customerId,
        CustomerName name,
        CustomerEmail email,
        CustomerGender gender,
        LocalDate dob,
        Kyc kyc,
        Address address,
        Contact contact,
        CustomerSegmentId customerSegmentId
) {
}
