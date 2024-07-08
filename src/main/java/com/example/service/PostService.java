package com.example.service;

import com.example.model.Post;
import io.micrometer.observation.annotation.Observed;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Observed(name = "postService")
@Service
public class PostService {

    private final RestTemplate restTemplate;

    private final RestClient restClient;

    public PostService(RestTemplate restTemplate, RestClient restClient) {
        this.restTemplate = restTemplate;
        this.restClient = restClient;
    }

    //use restTemplate
    public List<Post> loadPosts() {
        ResponseEntity<List<Post>> exchange = restTemplate.exchange("https://jsonplaceholder.typicode.com/posts", HttpMethod.GET, null, new ParameterizedTypeReference<List<Post>>() {});
        return exchange.getBody();
    }

    //use restClient
    public List<Post> postsByClient() {
       return restClient.get()
                .uri("https://jsonplaceholder.typicode.com/posts")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(List.class);
    }
}

