package hongleap.customer_service.domain.aggregate;

import hongleap.common.domain.valueObject.CustomerId;
import hongleap.common.domain.valueObject.CustomerSegmentId;
import hongleap.customer_service.domain.command.ChangePhoneNumberCommand;
import hongleap.customer_service.domain.command.CreateCustomerCommand;
import hongleap.customer_service.domain.event.CustomerCreatedEvent;
import hongleap.customer_service.domain.event.CustomerPhoneNumberChangedEvent;
import hongleap.customer_service.domain.valueobject.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.time.LocalDate;
import java.util.List;

@Aggregate // only get
@NoArgsConstructor
@Getter
@Slf4j
@EqualsAndHashCode
public class CustomerAggregate {

    @AggregateIdentifier
    private CustomerId customerId;
    private CustomerName name;
    private CustomerGender gender;
    private LocalDate dob;
    private Kyc kyc;
    private String phoneNumber;
    private Address address;
    private Contact contact;
    private CustomerSegmentId customerSegmentId;
    private List<String> failureMessage;

    // Domain logic for creating customer
    @CommandHandler
    public CustomerAggregate(CreateCustomerCommand createCustomerCommand){
        //Perform domain logic

        // Publish event -> CustomerCreatedEvent
        CustomerCreatedEvent customerCreatedEvent = CustomerCreatedEvent.builder()
                .customerId(createCustomerCommand.customerId())
                .name(createCustomerCommand.name())
                .gender(createCustomerCommand.gender())
                .dob(createCustomerCommand.dob())
                .kyc(createCustomerCommand.kyc())
                .address(createCustomerCommand.address())
                .contact(createCustomerCommand.contact())
                .build();

        AggregateLifecycle.apply(customerCreatedEvent);
    }

    @CommandHandler
    public void handler(ChangePhoneNumberCommand changePhoneNumberCommand){
        log.info("Handle ChangePhoneNumberCommand: {}", changePhoneNumberCommand);

        CustomerPhoneNumberChangedEvent customerPhoneNumberChangedEvent = CustomerPhoneNumberChangedEvent.builder()
                .customerId(changePhoneNumberCommand.customerId())
                .phoneNumber(changePhoneNumberCommand.phoneNumber())
                .build();
    }

    @EventSourcingHandler
    public void on(CustomerCreatedEvent customerCreatedEvent){
        this.customerId = customerCreatedEvent.customerId();
        this.name = customerCreatedEvent.name();
        this.gender = customerCreatedEvent.gender();
        this.dob = customerCreatedEvent.dob();
        this.kyc = customerCreatedEvent.kyc();
        this.address = customerCreatedEvent.address();
        this.contact = customerCreatedEvent.contact();
    }

    @EventSourcingHandler
    public void on(CustomerPhoneNumberChangedEvent customerPhoneNumberChangedEvent){
        this.customerId = customerPhoneNumberChangedEvent.customerId();
        this.phoneNumber = customerPhoneNumberChangedEvent.phoneNumber();
    }
}
