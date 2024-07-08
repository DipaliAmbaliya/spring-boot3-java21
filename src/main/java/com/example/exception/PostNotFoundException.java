package com.example.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
@Data
public class PostNotFoundException extends RuntimeException {

    private String data;

    public PostNotFoundException(String data) {
        super("Post not found!");
    }
}
