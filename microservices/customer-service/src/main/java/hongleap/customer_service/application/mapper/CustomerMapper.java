package hongleap.customer_service.application.mapper;

import hongleap.common.domain.valueObject.CustomerId;
import hongleap.customer_service.application.dto.create.CreateCustomerRequest;
import hongleap.customer_service.application.dto.query.CustomerResponse;
import hongleap.customer_service.application.dto.query.PageResponse;
import hongleap.customer_service.data.entity.CustomerEntity;
import hongleap.customer_service.domain.command.CreateCustomerCommand;
import hongleap.customer_service.domain.event.CustomerCreatedEvent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerResponse customerEntityToCustomerResponse(CustomerEntity customerEntity);

    CreateCustomerCommand createCustomerRequestToCreateCustomerCommand
            (CustomerId customerId, CreateCustomerRequest createCustomerRequest);

    @Mapping(source = "customerId.value", target = "customerId")
    CustomerEntity customerCreatedEventToCustomerEntity(CustomerCreatedEvent customerCreatedEvent);

//    default PageResponse toPageResponse(Page<CustomerEntity> customerEntityPage) {
//
//        List<CustomerResponse> data = customerEntityPage
//                .map(this::customerEntityToCustomerResponse)
//                .toList();
//
//        return PageResponse.builder()
//                .data(data)
//                .pageNumber(customerEntityPage.getNumber())
//                .pageSize(customerEntityPage.getSize())
//                .size(customerEntityPage.getNumberOfElements())
//                .totalRecords(customerEntityPage.getTotalElements())
//                .totalPages(customerEntityPage.getTotalPages())
//                .build();
//    }

}
