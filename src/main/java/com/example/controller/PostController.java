package com.example.controller;

import com.example.exception.PostNotFoundException;
import com.example.model.Post;
import com.example.repository.PostRepository;
import com.example.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @GetMapping("/{id}")
    public Post findById(@PathVariable Integer id) {
        return postRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
    }
    //Load Posts by restTemplate
    @GetMapping("/loadPosts")
    public List<Post> findByLoadPosts() {
        return postService.loadPosts();
    }
}
