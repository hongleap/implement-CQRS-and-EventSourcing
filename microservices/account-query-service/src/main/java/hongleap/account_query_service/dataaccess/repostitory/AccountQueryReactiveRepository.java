package hongleap.account_query_service.dataaccess.repostitory;

import hongleap.account_query_service.dataaccess.entity.AccountEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

import java.util.UUID;

public interface AccountQueryReactiveRepository extends R2dbcRepository<AccountEntity, UUID> {

}
