package hongleap.customer_service.application.projection;

import hongleap.customer_service.application.dto.query.CustomerPageResponse;
import hongleap.customer_service.application.dto.query.CustomerResponse;
import hongleap.customer_service.application.mapper.CustomerMapper;
import hongleap.customer_service.data.entity.CustomerEntity;
import hongleap.customer_service.data.entity.CustomerSegmentEntity;
import hongleap.customer_service.data.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class CustomerQueryHandler {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @QueryHandler
    public Page<CustomerResponse> handle(GetCustomerQuery getCustomerQuery){
        Pageable pageable = PageRequest.of(
                getCustomerQuery.getPageNumber(),
                getCustomerQuery.getPageSize(),
               Sort.by(Sort.Direction.DESC, "dob")
        );

        Page<CustomerEntity> customers = customerRepository
                .findAll(pageable);

        return customers.map(customerMapper::customerEntityToCustomerResponse);
    }
}
