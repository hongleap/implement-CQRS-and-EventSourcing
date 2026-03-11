package hongleap.account_query_service.domain.entity;

import hongleap.common.domain.valueObject.Money;
import hongleap.account_query_service.dataaccess.entity.AccountTypeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import hongleap.common.domain.valueObject.AccountTypeCode;
import hongleap.common.domain.valueObject.AccountStatus;
import hongleap.common.domain.valueObject.Currency;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class Account {

    private UUID accountId;
    private UUID customerId;
    private UUID branchId;

    private String accountNumber;
    private String accountHolder;

    private AccountTypeCode accountTypeCode;

    private AccountStatus status;

    private Money money;

    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
    private String createdBy;
    private String updatedBy;

    private AccountTypeEntity accountType;
}
