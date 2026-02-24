package hongleap.pipeline_service.client;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import java.util.Map;
import java.util.Objects;

@HttpExchange("/api/v1/accounts")
public interface AccountClient {

    @GetExchange
    Map<String, Object> getSecuredData();

}
