package com.example.config.webconfig;

import org.apache.hc.client5.http.impl.async.CloseableHttpAsyncClient;
import org.apache.hc.client5.http.impl.async.HttpAsyncClientBuilder;
import org.apache.hc.client5.http.impl.async.HttpAsyncClients;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.HttpComponentsClientHttpConnector;
import org.springframework.web.client.RestClient;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebConfig {

  @Autowired
  CloseableHttpClient httpClient;

  @Bean
  public WebClient webClient() {
    HttpAsyncClientBuilder clientBuilder = HttpAsyncClients.custom();
    CloseableHttpAsyncClient client = clientBuilder.build();
    ClientHttpConnector connector = new HttpComponentsClientHttpConnector(client);
    return WebClient.builder().clientConnector(connector).build();
  }

  @Bean
  RestClient restClient() {
    return RestClient.builder()
            .baseUrl("https://jsonplaceholder.typicode.com")
            .requestFactory(clientHttpRequestFactory())
            .build();
  }

  @Bean
  public HttpComponentsClientHttpRequestFactory clientHttpRequestFactory() {
    HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
    clientHttpRequestFactory.setHttpClient(httpClient);
    return clientHttpRequestFactory;
  }
}
