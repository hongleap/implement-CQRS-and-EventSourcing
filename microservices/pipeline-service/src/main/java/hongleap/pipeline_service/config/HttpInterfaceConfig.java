package hongleap.pipeline_service.config;

import hongleap.pipeline_service.client.AccountClient;
import hongleap.pipeline_service.client.JsonPlaceholderClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class HttpInterfaceConfig {

    // config client jsonplaceholder endpoint
    @Bean
    public JsonPlaceholderClient jsonPlaceholderClient(HttpInterfaceFactory factory) {
        return factory.createLoadNormalClient("https://jsonplaceholder.typicode.com",
                JsonPlaceholderClient.class);
    }

    // config client account endpoint
    @Bean
    public AccountClient accountClient(HttpInterfaceFactory factory) {
        return factory.createLoadBalancedClient("http://account",
                AccountClient.class);
    }
}

