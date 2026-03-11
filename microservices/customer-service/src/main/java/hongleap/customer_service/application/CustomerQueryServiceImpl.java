package hongleap.customer_service.application;

import hongleap.customer_service.application.dto.query.CustomerPageResponse;
import hongleap.customer_service.application.dto.query.CustomerResponse;
import hongleap.customer_service.application.mapper.CustomerMapper;
import hongleap.customer_service.application.projection.GetCustomerByIdQuery;
import hongleap.customer_service.application.projection.GetCustomerQuery;
import hongleap.customer_service.data.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.messaging.Message;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.modelling.command.AggregateNotFoundException;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.management.Query;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerQueryServiceImpl implements CustomerQueryService {

    private final QueryGateway queryGateway;
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final EventStore eventStore;

//    @Override
//    public Page<CustomerResponse> getAllCustomers(int pageNumber, int pageSize) {
//
//        GetCustomerQuery getCustomerQuery = new GetCustomerQuery();
//        getCustomerQuery.setPageNumber(pageNumber);
//        getCustomerQuery.setPageSize(pageSize);
//
//        List<CustomerResponse> customerResponses = queryGateway
//                .query(getCustomerQuery, ResponseTypes.multipleInstancesOf(CustomerResponse.class))
//                .join();
//
//        return new PageImpl<>(customerResponses, PageRequest.of(pageNumber, pageSize),
//                customerResponses.size());
//
//    }

    @Override
    public Page<CustomerResponse> getAllCustomers(int pageNumber, int pageSize) {

        GetCustomerQuery getCustomerQuery = new GetCustomerQuery();
        getCustomerQuery.setPageNumber(pageNumber);
        getCustomerQuery.setPageSize(pageSize);

        List<CustomerResponse> customers = queryGateway
                .query(getCustomerQuery, ResponseTypes.multipleInstancesOf(CustomerResponse.class))
                .join();

        return new PageImpl<>(customers, PageRequest.of(pageNumber, pageSize), customers.size());
    }

    @Override
    public List<?> getAllCustomerHistory(UUID customerId) {

        return eventStore.readEvents(customerId.toString())
                .asStream()
                .map(Message::getPayload)
                .toList();

    }

    @Override
    public CustomerResponse getCustomerById(UUID customerId) {
        GetCustomerByIdQuery query = new GetCustomerByIdQuery(customerId);
        return queryGateway
                .query(query, ResponseTypes.instanceOf(CustomerResponse.class))
                .join();
    }

}
