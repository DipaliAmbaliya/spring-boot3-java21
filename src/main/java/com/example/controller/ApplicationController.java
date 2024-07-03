package com.example.controller;


import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/")
public class ApplicationController {

    @Value("${spring.application.name}")
    private String applicationName;

    private final Logger LOGGER = LoggerFactory.getLogger(ApplicationController.class);

    @GetMapping(path = "hello")
    public String getValue(){
        visitCounter.increment();
        LOGGER.info("Thread Info: {}", Thread.currentThread());
        String response  = String.valueOf(Thread.currentThread().isVirtual());
        LOGGER.info("Response: {}", response);
        return response;
    }
    Counter visitCounter;

    public ApplicationController(MeterRegistry registry) {
        visitCounter = Counter.builder("visit_counter")
                .description("Number of visits to the Hello")
                .register(registry);
    }
    @GetMapping("/unmodifiableSet")
    Collection<User> unmodifiableSet() {

        Collection<User> customers = Set.of(new User(1, "A"), new User(2, "B"), new User(3, "C"));
        return Collections
                .unmodifiableSet(customers.stream().collect(Collectors.toSet()));

    }

    record User(Integer id, String name) {
    }

}
