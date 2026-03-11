package hongleap.account_query_service.application.ports.input.message.listener;

import hongleap.common.domain.event.AccountCreatedEvent;

public interface AccountMessageListener {

    void onAccountCreatedEvent(AccountCreatedEvent accountCreatedEvent);
}
