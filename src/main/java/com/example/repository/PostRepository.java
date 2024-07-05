package com.example.repository;

import com.example.model.Post;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

public interface PostRepository extends ListCrudRepository<Post,Integer>, ListPagingAndSortingRepository<Post,Integer> {

}
