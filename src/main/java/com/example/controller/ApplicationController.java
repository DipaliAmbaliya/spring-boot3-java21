package com.example.controller;


import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("api/v1/")
public class ApplicationController {

    private final Logger LOGGER = LoggerFactory.getLogger(ApplicationController.class);

    private static final int SLEEP_TIME = 100;
    Counter visitCounter;

    public ApplicationController(MeterRegistry registry) {
        visitCounter = Counter.builder("visit_counter")
                .description("Number of visits to the unmodifiableSet")
                .register(registry);
    }

    @GetMapping(path = "/isVirtual")
    public ResponseEntity<String> isVirtual(){
        visitCounter.increment();
        try {
            TimeUnit.MILLISECONDS.sleep(SLEEP_TIME);
        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage());
        }
        String response  = String.valueOf(Thread.currentThread().isVirtual());
        LOGGER.info("Response: {}", response);
        return ResponseEntity.ok(response);
    }

}
