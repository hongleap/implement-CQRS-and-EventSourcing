package hongleap.account_query_service.dataaccess.mapper;

import hongleap.account_query_service.dataaccess.entity.AccountEntity;
import hongleap.account_query_service.domain.entity.Account;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountDataAccessMapper {

    AccountEntity accountToAccountEntity(Account account);

    Account accountEntityToAccount(AccountEntity accountEntity);
}
