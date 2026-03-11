package hongleap.account_service.data.repository;

import hongleap.account_service.data.entity.AccountTypeEntity;
import hongleap.common.domain.valueObject.AccountTypeCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountTypeRepository extends JpaRepository<AccountTypeEntity, UUID> {

    Optional<AccountTypeEntity> findByAccountTypeCode(AccountTypeCode accountTypeCode);

}
