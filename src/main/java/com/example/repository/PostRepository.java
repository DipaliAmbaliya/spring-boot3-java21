package com.example.repository;

import com.example.model.Post;
import org.springframework.data.repository.ListCrudRepository;

public interface PostRepository extends ListCrudRepository<Post,Integer> {

}
