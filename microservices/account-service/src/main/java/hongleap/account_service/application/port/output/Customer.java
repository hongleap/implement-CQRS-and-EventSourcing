package hongleap.account_service.application.port.output;

import hongleap.account_service.application.dto.customer.CustomerResponse;

import java.util.Optional;

public interface Customer {
    Optional<CustomerResponse> getCustomerById(String customerId);
}
