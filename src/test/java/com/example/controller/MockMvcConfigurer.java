package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.web.servlet.MockMvc;

@Configuration
public class MockMvcConfigurer {

    @Autowired
    private MockMvc mockMvc;

    @Bean
    public MockMvcOnVirtualThreads mockMvcOnVirtualThreads() {
        return new MockMvcOnVirtualThreads(mockMvc);
    }

}
