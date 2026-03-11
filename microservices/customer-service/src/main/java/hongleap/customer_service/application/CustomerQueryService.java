package hongleap.customer_service.application;

import hongleap.customer_service.application.dto.query.CustomerPageResponse;
import hongleap.customer_service.application.dto.query.CustomerResponse;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface CustomerQueryService {
    Page<CustomerResponse> getAllCustomers(int pageNumber, int pageSize);

    List<?> getAllCustomerHistory(UUID customerId);

    CustomerResponse getCustomerById(UUID customerId);
}
