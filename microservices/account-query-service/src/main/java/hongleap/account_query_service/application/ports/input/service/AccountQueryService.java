package hongleap.account_query_service.application.ports.input.service;

import hongleap.account_query_service.application.dto.AccountQueryResponse;
import hongleap.account_query_service.domain.entity.Account;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface AccountQueryService {

    Mono<AccountQueryResponse> getAccountById(UUID accountId);
}
