package com.example.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {ApplicationController.class,MockMvcOnVirtualThreads.class})
@AutoConfigureMockMvc
public class ApplicationControllerTest {

    @Autowired
    protected MockMvcOnVirtualThreads mockMvc;

    @Test
    void getHello() throws Exception {

        String expected = "true";

        mockMvc.perform(get("/api/v1/hello"))
                .andExpect(content().string(expected));

    }
}
