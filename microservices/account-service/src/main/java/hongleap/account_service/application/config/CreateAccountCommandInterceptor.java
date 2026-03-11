package hongleap.account_service.application.config;


import hongleap.account_service.application.client.CustomerClient;
import hongleap.account_service.application.dto.create.response.CustomerResponse;
import hongleap.account_service.domain.command.CreateAccountCommand;
import hongleap.common.domain.valueObject.CustomerId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.MessageDispatchInterceptor;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

@Component
@RequiredArgsConstructor
@Slf4j
public class CreateAccountCommandInterceptor
        implements MessageDispatchInterceptor<CommandMessage<?>> {

    private final CustomerClient customerClient; // ← your RestClient wrapper

    @Override
    public BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> handle(
            List<? extends CommandMessage<?>> messages) {
//
        return (index, command) -> {

            if (command.getPayload() instanceof CreateAccountCommand createCmd) {

                log.info("Validating customerId [{}] via RestClient",
                        createCmd.customerId());

                CustomerResponse customer = customerClient
                        .getCustomerById(createCmd.customerId().toString())  // ✅ fix from previous step
                        .orElseThrow(() -> new IllegalStateException(
                                "Customer not found: " + createCmd.customerId()
                        ));


                // Enrich metadata and pass through
                return command.andMetaData(Map.of(
                        "customerId", customer.customerId(),
                        "validatedAt", Instant.now().toString()
                ));
            }

            return command;
        };
    }

}