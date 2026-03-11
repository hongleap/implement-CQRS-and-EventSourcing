package hongleap.account_query_service.application.adapter;

import hongleap.account_query_service.application.mapper.AccountAppDataMapper;
import hongleap.account_query_service.application.ports.input.message.listener.AccountMessageListener;
import hongleap.account_query_service.application.ports.input.service.AccountQueryService;
import hongleap.account_query_service.application.ports.output.repository.AccountQueryRepository;
import hongleap.account_query_service.domain.entity.Account;
import hongleap.common.domain.event.AccountCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class AccountMessageListenerImpl implements AccountMessageListener {

    private final AccountQueryRepository accountQueryRepository;
    private final AccountAppDataMapper accountAppDataMapper;

    @Override
    public void onAccountCreatedEvent(AccountCreatedEvent accountCreatedEvent) {
        Account account = accountAppDataMapper.accountCreatedEventToAccount(accountCreatedEvent);
        accountQueryRepository.save(account)
                .doOnSuccess(data ->
                        log.info("Saved Account = {} successfully", accountCreatedEvent.accountId()));
    }
}
