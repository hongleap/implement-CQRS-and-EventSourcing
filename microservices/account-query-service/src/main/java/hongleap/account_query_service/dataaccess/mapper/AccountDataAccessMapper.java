package hongleap.account_query_service.dataaccess.mapper;

import hongleap.account_query_service.dataaccess.entity.AccountEntity;
import hongleap.account_query_service.domain.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountDataAccessMapper {

    @Mapping(constant = "true", target = "new")
    AccountEntity accountToAccountEntity(Account account);

    Account accountEntityToAccount(AccountEntity accountEntity);
}
