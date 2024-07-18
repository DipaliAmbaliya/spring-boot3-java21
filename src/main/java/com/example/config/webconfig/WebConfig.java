package com.example.config.webconfig;

import com.example.service.JsonPlaceholderService;
import lombok.SneakyThrows;
import org.springframework.boot.autoconfigure.web.reactive.function.client.WebClientSsl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class WebConfig {

  @Bean
  WebClient webClient(WebClientSsl ssl) {
    /*To customize Reactor Netty settings, provide a pre-configured HttpClient:
    * */
//    HttpClient httpClient = HttpClient.create().secure(sslSpec -> ...);
//
//    WebClient webClient = WebClient.builder()
//            .clientConnector(new ReactorClientHttpConnector(httpClient))
//            .build();
    return WebClient.builder()
            .exchangeStrategies(ExchangeStrategies.builder().codecs(c ->
                    c.defaultCodecs().enableLoggingRequestDetails(true)).build()
            )
            .baseUrl("https://jsonplaceholder.typicode.com/").apply(ssl.fromBundle("demo"))
            .build();

  }

  @SneakyThrows
  @Bean
  JsonPlaceholderService postClient(WebClient webClient) {
    HttpServiceProxyFactory httpServiceProxyFactory =
            HttpServiceProxyFactory.builderFor(WebClientAdapter.create(webClient))
                    .build();
    return httpServiceProxyFactory.createClient(JsonPlaceholderService.class);
  }

}
