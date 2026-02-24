package hongleap.customer_service.domain.event;

import hongleap.common.domain.valueObject.CustomerId;
import lombok.Builder;

@Builder
public record CustomerPhoneNumberChangedEvent(
        CustomerId customerId,
        String phoneNumber
) {
}
