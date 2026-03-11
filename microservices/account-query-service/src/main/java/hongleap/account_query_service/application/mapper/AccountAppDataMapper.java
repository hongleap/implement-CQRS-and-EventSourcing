package hongleap.account_query_service.application.mapper;

import hongleap.common.domain.event.AccountCreatedEvent;
import hongleap.account_query_service.application.dto.AccountQueryResponse;
import hongleap.account_query_service.domain.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountAppDataMapper {

    AccountQueryResponse accountToAccountQueryResponse(Account account);

    @Mapping(source = "accountId.value", target = "accountId")
    @Mapping(source = "customerId.value", target = "customerId")
    @Mapping(source = "branchId.value", target = "branchId")
    Account accountCreatedEventToAccount(AccountCreatedEvent accountCreatedEvent);
}
