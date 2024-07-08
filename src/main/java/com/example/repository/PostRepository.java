package com.example.repository;

import com.example.model.Post;
import com.example.records.PostRecords;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends ListCrudRepository<Post,Integer>, ListPagingAndSortingRepository<Post,Integer> {

    Optional<PostRecords> findPostByTitle(String title);

}
