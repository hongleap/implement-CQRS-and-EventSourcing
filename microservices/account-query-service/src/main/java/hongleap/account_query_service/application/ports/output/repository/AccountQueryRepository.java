package hongleap.account_query_service.application.ports.output.repository;

import hongleap.account_query_service.domain.entity.Account;
import reactor.core.publisher.Mono;

import java.util.UUID;

// Output port for data access technologies
public interface AccountQueryRepository {

    Mono<Account> save(Account account);

    Mono<Account> findById(UUID accountId);
}
