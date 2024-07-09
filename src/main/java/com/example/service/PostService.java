package com.example.service;

import com.example.model.Post;
import io.micrometer.observation.annotation.Observed;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Observed(name = "postService")
@Service
public class PostService {

    private final RestTemplate restTemplate;

    private final WebClient webClient;

    public PostService(RestTemplate restTemplate, WebClient webClient) {
        this.restTemplate = restTemplate;
        this.webClient = webClient;
    }

    //use restTemplate
    public List<Post> loadPosts() {
        ResponseEntity<List<Post>> exchange = restTemplate.exchange("https://jsonplaceholder.typicode.com/posts", HttpMethod.GET, null, new ParameterizedTypeReference<List<Post>>() {});
        return exchange.getBody();
    }

    //use webClient
    public List<Post> postsByClient() {
       return webClient.get()
                .uri("https://jsonplaceholder.typicode.com/posts")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve().bodyToMono(List.class).block();
    }
}

