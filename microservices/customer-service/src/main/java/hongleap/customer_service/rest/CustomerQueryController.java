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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
@Slf4j
public class CustomerQueryController {

    private final CustomerQueryService customerQueryService;

    @GetMapping("/pagination")
    public Page<CustomerResponse> getAllCustomers(
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "25", required = false) int pageSize
    ){
        return customerQueryService.getAllCustomers(pageNumber, pageSize);
    }
}
