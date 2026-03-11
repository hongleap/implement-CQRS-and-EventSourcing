package hongleap.account_service.data.entity;

import hongleap.account_service.domain.valueObject.CustomerName;
import hongleap.common.domain.valueObject.CustomerId;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "customers")
public class CustomerEntity {

    @Id
    private UUID customerId;

    @Embedded
    private CustomerName customerName;

    private String phoneNumber;
}
