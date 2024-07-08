package com.example.controller;

import com.example.exception.PostNotFoundException;
import com.example.model.Post;
import com.example.repository.PostRepository;
import com.example.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostRepository postRepository;

    private final PostService postService;

    @Autowired
    RestClient restClient;

    public PostController(PostRepository postRepository, PostService postService) {
        this.postRepository = postRepository;
        this.postService = postService;
    }

    @GetMapping
    public ResponseEntity<List<Post>> findAll() {
        return ResponseEntity.ok(postRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> findById(@PathVariable Integer id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
        return ResponseEntity.ok(post);
    }
    //Load Posts by restTemplate
    @GetMapping("/loadPosts")
    public ResponseEntity<List<Post>> findByLoadPosts() {
        return ResponseEntity.ok(postService.loadPosts());
    }

    @GetMapping("/postsByClient")
    public ResponseEntity<List<Post>> postsByClient() {
        List<Post> posts = restClient.get()
                .uri("https://jsonplaceholder.typicode.com/posts")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(List.class);
        return ResponseEntity.ok(posts);
    }


}
