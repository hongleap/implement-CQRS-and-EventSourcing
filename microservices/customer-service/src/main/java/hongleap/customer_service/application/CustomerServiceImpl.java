package hongleap.customer_service.application;

import hongleap.common.domain.valueObject.CustomerId;
import hongleap.customer_service.application.dto.create.CreateCustomerRequest;
import hongleap.customer_service.application.dto.create.CreateCustomerResponse;
import hongleap.customer_service.application.dto.update.ChangePhoneNumberRequest;
import hongleap.customer_service.application.dto.update.ChangePhoneNumberResponse;
import hongleap.customer_service.application.mapper.CustomerMapper;
import hongleap.customer_service.domain.command.ChangePhoneNumberCommand;
import hongleap.customer_service.domain.command.CreateCustomerCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerMapper customerMapper;
    private final CommandGateway commandGateway;

    @Override
    public ChangePhoneNumberResponse changePhoneNumber(UUID customerId, ChangePhoneNumberRequest changePhoneNumberRequest) {

        // 1. Transfer data from request to command
        ChangePhoneNumberCommand changePhoneNumberCommand = ChangePhoneNumberCommand.builder()
                .customerId(new CustomerId(customerId))
                .phoneNumber(changePhoneNumberRequest.phoneNumber())
                .build();
        log.info("ChangePhoneNumberCommand: {}", changePhoneNumberCommand);

        UUID result = commandGateway.sendAndWait(changePhoneNumberCommand);

        return ChangePhoneNumberResponse.builder()
                .customerId(result)
                .phoneNumber(changePhoneNumberCommand.phoneNumber())
                .message("Phone number changed successfully")
                .build();
    }

    @Override
    public CreateCustomerResponse createCustomer(CreateCustomerRequest createCustomerRequest) {

        // 1. Transfer data from request to command
        CreateCustomerCommand createCustomerCommand = customerMapper
                .createCustomerRequestToCreateCustomerCommand(new CustomerId(UUID.randomUUID()),createCustomerRequest);
        log.info("CreateCustomerCommand: {}", createCustomerCommand);

        // 2. Invoke and handle Axon command gateway
        CustomerId result = commandGateway.sendAndWait(createCustomerCommand);
        log.info("CommandGateway Result: {}", result);

        return CreateCustomerResponse.builder()
                .customerId(createCustomerCommand.customerId().value())
                .message("Customer saved successfully")
                .build();
    }

}