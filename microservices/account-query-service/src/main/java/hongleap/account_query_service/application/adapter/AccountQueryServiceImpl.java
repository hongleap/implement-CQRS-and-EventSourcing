package hongleap.account_query_service.application.adapter;

import hongleap.account_query_service.application.dto.AccountQueryResponse;
import hongleap.account_query_service.application.mapper.AccountAppDataMapper;
import hongleap.account_query_service.application.ports.input.service.AccountQueryService;
import hongleap.account_query_service.application.ports.output.repository.AccountQueryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountQueryServiceImpl implements AccountQueryService {

    private final AccountAppDataMapper accountAppDataMapper;
    private final AccountQueryRepository accountQueryRepository;

    @Override
    public Mono<AccountQueryResponse> getAccountById(UUID accountId) {
        return accountQueryRepository
                .findById(accountId)
                .map(accountAppDataMapper::accountToAccountQueryResponse);
    }


}
