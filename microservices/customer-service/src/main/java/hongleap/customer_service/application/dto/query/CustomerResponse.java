package hongleap.customer_service.application.dto.query;

import hongleap.common.domain.valueObject.CustomerSegmentId;
import hongleap.customer_service.domain.valueobject.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record CustomerResponse(
        UUID customerId,
        @NotNull
        CustomerName name,
        @NotNull
        CustomerEmail email,
        @NotNull
        CustomerGender gender,
        @NotNull
        LocalDate dob,
        @NotNull
        Kyc kyc,
        @NotNull
        Address address,
        @NotNull
        Contact contact,
        @NotBlank
        String phoneNumber,
        @NotNull
        CustomerSegmentResponse customerSegment
) {
}
