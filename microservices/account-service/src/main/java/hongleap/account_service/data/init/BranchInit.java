package hongleap.account_service.data.init;

import hongleap.account_service.data.entity.BranchEntity;
import hongleap.account_service.data.repository.BranchRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class BranchInit {

    private final BranchRepository branchRepository;
    @PostConstruct
    public void initCustomerSegment(){
        // Seed branches
        if (branchRepository.count() == 0) {
            BranchEntity branch = new BranchEntity();
            branch.setId(UUID.randomUUID()); // same UUID you use in Postman
            branch.setBranchName("Phnom Penh");
            branch.setIsOpened(true);
            branchRepository.save(branch);
            log.info("Branch seeded");
        }
    }
}