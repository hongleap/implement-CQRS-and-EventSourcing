package hongleap.customer_service.application.dto.create;

import hongleap.common.domain.valueObject.CustomerSegmentId;
import hongleap.customer_service.domain.valueobject.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import tools.jackson.databind.node.ContainerNode;

import java.time.LocalDate;

public record CreateCustomerRequest(
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
    CustomerSegmentId customerSegmentId
){
}
