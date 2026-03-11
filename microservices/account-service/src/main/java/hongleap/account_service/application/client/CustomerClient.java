package hongleap.account_service.application.client;

import hongleap.account_service.application.dto.create.response.CustomerResponse;
import hongleap.common.domain.valueObject.CustomerId;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.util.Optional;

import static reactor.netty.http.HttpConnectionLiveness.log;

@Component
public class CustomerClient {

    private final WebClient webClient;

    public CustomerClient(@Value("${customer.service.url}") String customerServiceUrl) {
        this.webClient = WebClient.builder()
                .baseUrl(customerServiceUrl)  // ✅ resolved from application.properties
                .build();
    }

    public Optional<CustomerResponse> getCustomerById(String customerId) {  // ✅ accepts String
        return webClient.get()
                .uri("/api/customers/{id}", customerId)
                .retrieve()
                .bodyToMono(CustomerResponse.class)
                .onErrorResume(WebClientResponseException.NotFound.class, ex -> {
                    log.warn("Customer not found: {}", customerId);
                    return Mono.empty();
                })
                .onErrorResume(WebClientResponseException.class, ex -> {
                    log.error("HTTP error fetching customer [{}]: {}", customerId, ex.getStatusCode());
                    return Mono.error(new IllegalStateException(
                            "Customer service error: " + ex.getStatusCode()));
                })
                .blockOptional();
    }

//    private final RestClient restClient;
//
//    private String customerServiceUrl;
//
//    // Inject base URL from application.properties
//    public CustomerClient(
//            @Value("${customer.service.url}") String baseUrl) {
//
//        this.restClient = RestClient.builder()
//                .baseUrl(baseUrl)
//                .build();
//    }
//
//    public CustomerResponse getCustomerById(String customerId) {
//        try {
//            return restClient.get()
//                    .uri("/api/customers/{customerId}", customerId)
//                    .retrieve()
//                    .body(CustomerResponse.class);
//
//        } catch (Exception e) {
//            log.error("Failed to fetch customer [{}]: {}", customerId, e.getMessage());
//            return null; // interceptor will block if null
//        }
//    }


}