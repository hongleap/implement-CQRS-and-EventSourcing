package hongleap.pipeline_service.config;

import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Component
@RequiredArgsConstructor
public class HttpInterfaceFactory {

    private final OAuth2AuthorizedClientManager oAuth2AuthorizedClientManager;
    private final WebClient.Builder  loadBalancedWebClientBuilder;

    // create normal client method
    public <T> T createLoadNormalClient(String baseUrl, Class<T> interfaceClass){
        WebClient webClient = WebClient.builder()
                .baseUrl(baseUrl)
                .build();

        return createClient(webClient, interfaceClass);
    }

    // 2nd method
    public <T> T createLoadBalancedClient(String baseUrl, Class<T> interfaceClass) {

        var oauth2 = new ServletOAuth2AuthorizedClientExchangeFilterFunction(
                oAuth2AuthorizedClientManager
        );
        oauth2.setDefaultClientRegistrationId("itp-standard");

        // create client objects
//        WebClient webClient = WebClient.builder()
//                .baseUrl(baseUrl)
//                .apply(oauth2.oauth2Configuration())
//                .build();
//
        // create client objects
        WebClient webClient = loadBalancedWebClientBuilder
                .baseUrl(baseUrl)
                .apply(oauth2.oauth2Configuration())
                .build();

        return createClient(webClient, interfaceClass);
    }

    // 1st method
    public <T> T createClient(WebClient webClient, Class<T> interfaceClass) {
        HttpServiceProxyFactory factory = HttpServiceProxyFactory
                .builderFor(WebClientAdapter.create(webClient))
                .build();
        return factory.createClient(interfaceClass);
    }
}

