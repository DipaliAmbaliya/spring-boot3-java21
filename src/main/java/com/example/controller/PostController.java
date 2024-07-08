package com.example.controller;

import com.example.exception.PostNotFoundException;
import com.example.model.Post;
import com.example.records.PostRecords;
import com.example.repository.PostRepository;
import com.example.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostRepository postRepository;

    private final PostService postService;

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
        Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id.toString()));
        return ResponseEntity.ok(post);
    }

    @GetMapping("/loadPosts")
    public ResponseEntity<List<Post>> findByLoadPosts() {
        return ResponseEntity.ok(postService.loadPosts());
    }

    @GetMapping("/postsByClient")
    public ResponseEntity<List<Post>> postsByClient() {
        return ResponseEntity.ok(postService.postsByClient());
    }

    @GetMapping("/findPostByTitle/{title}")
    public ResponseEntity<PostRecords> findPostByTitle(@PathVariable String title) {
        PostRecords postRecords = postRepository.findPostByTitle(title).orElseThrow(() -> new PostNotFoundException(title));
        return ResponseEntity.ok(postRecords);
    }

}
