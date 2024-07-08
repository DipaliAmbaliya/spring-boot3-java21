package com.example.controller;

import com.example.exception.PostNotFoundException;
import com.example.model.Post;
import com.example.repository.PostRepository;
import com.example.service.PostService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class PostControllerTest {

    @InjectMocks
    private PostController controller;

    private PostService postService = mock(PostService.class);
    private PostRepository postRepository = mock(PostRepository.class);


    @Test
    void findAllTest() {

        List<Post> expected = List.of(new Post(1, 1,"A","null"), new Post(2, 2,"B","null"), new Post(3,3, "C","null"));
        when(postRepository.findAll()).thenReturn(List.of(new Post(1, 1,"A","null"), new Post(2, 2,"B","null"), new Post(3,3, "C","null")));
        List<Post> posts = controller.findAll().getBody();
        assertEquals(expected.size(),posts.size());


    }

    @Test
    void findByIdPositive() {
        when(postRepository.findById(1)).thenReturn(Optional.of(new Post()));
        Post post = controller.findById(1).getBody();
        assertNotNull(post);
    }

    @Test
    void findByIdNagative() {
        when(postRepository.findById(101)).thenThrow( new PostNotFoundException("101"));
        assertThrows(PostNotFoundException.class, ()-> controller.findById(101),"Post not found!");
    }

    @Test
    void loadPostsTest() {
        List<Post> expected = List.of(new Post(1, 1,"A","null"), new Post(2, 2,"B","null"), new Post(3,3, "C","null"));
        when(postService.loadPosts()).thenReturn(List.of(new Post(1, 1,"A","null"), new Post(2, 2,"B","null"), new Post(3,3, "C","null")));
        List<Post> posts = controller.findByLoadPosts().getBody();
        assertEquals(expected.size(),posts.size());

    }
}
