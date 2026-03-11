package hongleap.account_query_service.dataaccess.adapter;

import hongleap.account_query_service.application.ports.output.repository.AccountQueryRepository;
import hongleap.account_query_service.dataaccess.entity.AccountEntity;
import hongleap.account_query_service.dataaccess.mapper.AccountDataAccessMapper;
import hongleap.account_query_service.dataaccess.repostitory.AccountQueryReactiveRepository;
import hongleap.account_query_service.domain.entity.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class AccountQueryRepositoryImpl implements AccountQueryRepository {

    private final AccountQueryReactiveRepository accountQueryReactiveRepository;
    private final AccountDataAccessMapper accountDataAccessMapper;

    @Override
    public Mono<Account> save(Account account) {

        AccountEntity accountEntity = accountDataAccessMapper
                .accountToAccountEntity(account);

        return accountQueryReactiveRepository
                .save(accountEntity)
                .map(accountDataAccessMapper::accountEntityToAccount);
    }

    @Override
    public Mono<Account> findById(UUID accountId) {
        return accountQueryReactiveRepository
                .findById(accountId)
                .map(accountDataAccessMapper::accountEntityToAccount);
    }
}
