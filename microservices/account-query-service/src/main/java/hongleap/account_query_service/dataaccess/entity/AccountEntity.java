package hongleap.account_query_service.dataaccess.entity;

import hongleap.common.domain.valueObject.Currency;
import hongleap.common.domain.valueObject.AccountStatus;
import hongleap.common.domain.valueObject.AccountTypeCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "accounts")
public class AccountEntity {

    @Id
    private UUID accountId;
    private UUID customerId;
    private UUID branchId;

    private String accountNumber;
    private String accountHolder;

    private AccountTypeCode accountTypeCode;

    private AccountStatus status;

    private BigDecimal balance;
    private Currency currency;

    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
    private String createdBy;
    private String updatedBy;

    private AccountTypeEntity accountType;

}
