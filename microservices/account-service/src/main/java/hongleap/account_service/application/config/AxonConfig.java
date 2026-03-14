package hongleap.account_service.application.config;

import hongleap.account_service.application.config.CreateAccountCommandInterceptor;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.common.jpa.EntityManagerProvider;
import org.axonframework.common.transaction.TransactionManager;
import org.axonframework.config.ConfigurerModule;
import org.axonframework.eventhandling.deadletter.jpa.JpaSequencedDeadLetterQueue;
import org.axonframework.eventsourcing.EventCountSnapshotTriggerDefinition;
import org.axonframework.eventsourcing.SnapshotTriggerDefinition;
import org.axonframework.eventsourcing.Snapshotter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class AxonConfig {

    private final CreateAccountCommandInterceptor interceptor;

    @Autowired
    public void registerInterceptors(CommandBus commandBus) {
        commandBus.registerDispatchInterceptor(interceptor);
    }

    @Bean
    public SnapshotTriggerDefinition customerSnapshotTriggerDefinition(Snapshotter snapshotter) {
        return new EventCountSnapshotTriggerDefinition(snapshotter, 5);
    }


}
