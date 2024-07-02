package com.example.controller;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.servlet.DispatcherServlet;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MockMvcOnVirtualThreads {

    private final MockMvc delegatee;
    private final ExecutorService executor;

    public MockMvcOnVirtualThreads(MockMvc delegatee) {
        this.delegatee = delegatee;
        this.executor = Executors.newVirtualThreadPerTaskExecutor();
    }

    public DispatcherServlet getDispatcherServlet() {
        return delegatee.getDispatcherServlet();
    }

    public ResultActions perform(RequestBuilder requestBuilder) throws Exception {
        return executor.submit(() -> delegatee.perform(requestBuilder)).get();
    }

}