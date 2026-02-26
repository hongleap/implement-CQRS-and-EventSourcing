package hongleap.customer_service.application;

import hongleap.customer_service.application.dto.query.CustomerPageResponse;
import hongleap.customer_service.application.dto.query.CustomerResponse;
import org.springframework.data.domain.Page;

public interface CustomerQueryService {
    Page<CustomerResponse> getAllCustomers(int pageNumber, int pageSize);
}
