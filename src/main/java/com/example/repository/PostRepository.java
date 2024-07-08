package com.example.repository;

import com.example.model.Post;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends ListCrudRepository<Post,Integer>, ListPagingAndSortingRepository<Post,Integer> {

}
