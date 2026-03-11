package hongleap.account_service.data.entity;

import hongleap.common.domain.valueObject.AccountTypeCode;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "accountTypes")
public class AccountTypeEntity {

    @Id
    private UUID accountTypeId;

    @Enumerated(EnumType.STRING)
    private AccountTypeCode accountTypeCode;

    @OneToMany(mappedBy = "accountType")
    private List<AccountEntity> accounts;
}


