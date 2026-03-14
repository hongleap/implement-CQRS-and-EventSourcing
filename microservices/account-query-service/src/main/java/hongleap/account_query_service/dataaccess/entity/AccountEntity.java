package hongleap.account_query_service.dataaccess.entity;

import hongleap.common.domain.valueObject.Money;
import hongleap.common.domain.valueObject.AccountStatus;
import hongleap.common.domain.valueObject.AccountTypeCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.jspecify.annotations.Nullable;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.annotation.Version;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "accounts")
@ToString
public class AccountEntity implements Persistable<UUID>{

    @Id
    private UUID accountId;
    private UUID customerId;
    private UUID branchId;

    private String accountNumber;
    private String accountHolder;

    private AccountTypeCode accountTypeCode;

    private AccountStatus status;

    private BigDecimal balance;
    private String currency;

    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
    private String createdBy;
    private String updatedBy;

    private AccountTypeEntity accountType;

//    @Version
//    private Long version;

    @Transient
    private boolean isNew;

    @Override
    public @Nullable UUID getId() {
        return accountId;
    }

    @Override
    public boolean isNew() {
        return isNew;
    }

}
