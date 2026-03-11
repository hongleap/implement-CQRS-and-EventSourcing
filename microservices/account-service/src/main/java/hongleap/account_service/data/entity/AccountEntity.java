package hongleap.account_service.data.entity;

import hongleap.common.domain.valueObject.AccountStatus;
import hongleap.common.domain.valueObject.AccountTypeCode;
import hongleap.common.domain.valueObject.Money;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "accounts")
public class AccountEntity {

    @Id
    private UUID accountId;
    private UUID customerId;
    private UUID branchId;

    private String accountNumber;
    private String accountHolder;

    @Enumerated(EnumType.STRING)
    private AccountTypeCode accountTypeCode;

    @Enumerated(EnumType.STRING)
    private AccountStatus status;

    @Embedded
    private Money initialBalance;

    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
    private String createdBy;
    private String updatedBy;


    @ManyToOne
    @JoinColumn(name = "account_type_id")
    private AccountTypeEntity accountType;

}
