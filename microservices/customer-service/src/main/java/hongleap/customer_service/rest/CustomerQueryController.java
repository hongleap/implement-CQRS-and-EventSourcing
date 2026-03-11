package hongleap.customer_service.rest;

import hongleap.customer_service.application.CustomerQueryService;
import hongleap.customer_service.application.dto.query.CustomerPageResponse;
import hongleap.customer_service.application.dto.query.CustomerResponse;
import hongleap.customer_service.application.projection.GetCustomerQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
@Slf4j
public class CustomerQueryController {

    private final CustomerQueryService customerQueryService;

    @GetMapping
    public Page<CustomerResponse> getAllCustomers(
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "25", required = false) int pageSize
    ){
        return customerQueryService.getAllCustomers(pageNumber, pageSize);
    }

    @GetMapping("/{customerId}/history")
    public List<?> getAllCustomerHistory (@PathVariable UUID customerId){
        return customerQueryService.getAllCustomerHistory(customerId);
    }

    @GetMapping("/{customerId}")
    public CustomerResponse getCustomerById(@PathVariable UUID customerId) {
        return customerQueryService.getCustomerById(customerId);
    }
}
