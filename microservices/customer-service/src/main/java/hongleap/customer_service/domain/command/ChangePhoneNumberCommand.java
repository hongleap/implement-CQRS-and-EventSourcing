package hongleap.customer_service.domain.command;

import hongleap.common.domain.valueObject.CustomerId;
import lombok.Builder;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Builder
public record ChangePhoneNumberCommand(

        @TargetAggregateIdentifier
        CustomerId customerId,
        String phoneNumber
) {
}
