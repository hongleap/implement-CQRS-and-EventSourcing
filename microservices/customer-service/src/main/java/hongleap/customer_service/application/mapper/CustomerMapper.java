package hongleap.customer_service.application.mapper;

import hongleap.common.domain.valueObject.CustomerId;
import hongleap.customer_service.application.dto.create.CreateCustomerRequest;
import hongleap.customer_service.domain.command.CreateCustomerCommand;
import org.mapstruct.Mapper;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CreateCustomerCommand createCustomerRequestToCreateCustomerCommand
            (CustomerId customerId, CreateCustomerRequest createCustomerRequest);
}
