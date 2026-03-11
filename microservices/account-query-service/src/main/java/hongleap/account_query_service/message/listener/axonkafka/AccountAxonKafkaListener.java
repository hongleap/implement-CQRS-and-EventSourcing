package hongleap.account_query_service.message.listener.axonkafka;

import hongleap.account_query_service.application.ports.input.message.listener.AccountMessageListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.config.ProcessingGroup;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
@ProcessingGroup("account-group")
public class AccountAxonKafkaListener   {

    private final AccountMessageListener accountMessageListener;

    public void on (hongleap.common.domain.event.AccountCreatedEvent accountCreatedEvent){
        log.info("On accountCreatedEvent: {}", accountCreatedEvent);
        accountMessageListener.onAccountCreatedEvent(accountCreatedEvent);
    }
}
