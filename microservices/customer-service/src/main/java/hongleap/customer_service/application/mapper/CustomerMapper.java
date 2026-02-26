package hongleap.customer_service.application.mapper;

import hongleap.common.domain.valueObject.CustomerId;
import hongleap.customer_service.application.dto.create.CreateCustomerRequest;
import hongleap.customer_service.application.dto.query.CustomerResponse;
import hongleap.customer_service.data.entity.CustomerEntity;
import hongleap.customer_service.domain.command.CreateCustomerCommand;
import hongleap.customer_service.domain.event.CustomerCreatedEvent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerResponse customerEntityToCustomerResponse(CustomerEntity customerEntity);

    CreateCustomerCommand createCustomerRequestToCreateCustomerCommand
            (CustomerId customerId, CreateCustomerRequest createCustomerRequest);

    @Mapping(source = "customerId.value", target = "customerId")
    CustomerEntity customerCreatedEventToCustomerEntity(CustomerCreatedEvent customerCreatedEvent);
}
