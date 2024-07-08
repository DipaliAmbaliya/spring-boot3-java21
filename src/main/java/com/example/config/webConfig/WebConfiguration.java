package com.example.config.webConfig;

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ssl.SslBundles;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Autowired
    CloseableHttpClient httpClient;

    /* Spring MVC and WebFlux URL Matching Changes */
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.setUseTrailingSlashMatch(true);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder, SslBundles sslBundles) {
        return restTemplateBuilder.setSslBundle(sslBundles.getBundle("demo")).build();
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
