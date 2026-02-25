package hongleap.customer_service.application;

import hongleap.customer_service.application.dto.create.CreateCustomerRequest;
import hongleap.customer_service.application.dto.create.CreateCustomerResponse;
import hongleap.customer_service.application.dto.update.ChangePhoneNumberRequest;
import hongleap.customer_service.application.dto.update.ChangePhoneNumberResponse;

import java.util.UUID;

public interface CustomerService {

    ChangePhoneNumberResponse changePhoneNumber(UUID customerId, ChangePhoneNumberRequest changePhoneNumberRequest);
    CreateCustomerResponse createCustomer(CreateCustomerRequest createCustomerRequest);

}
