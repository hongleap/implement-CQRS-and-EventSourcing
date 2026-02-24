package hongleap.customer_service.domain.command;

import hongleap.common.domain.valueObject.CustomerId;
import hongleap.common.domain.valueObject.CustomerSegmentId;
import hongleap.customer_service.domain.valueobject.*;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.time.LocalDate;

public record CreateCustomerCommand(

        @TargetAggregateIdentifier
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
