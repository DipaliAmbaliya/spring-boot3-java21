package com.example.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.metrics.CompositeMeterRegistryAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.metrics.MetricsAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collection;
import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {ApplicationController.class,MockMvcOnVirtualThreads.class},properties = {
        "security.basic.enabled=false"
})
@AutoConfigureMockMvc(addFilters = false)
@Import({MetricsAutoConfiguration.class, CompositeMeterRegistryAutoConfiguration.class})
@TestPropertySource(locations = {
        "classpath:/application-test.yml"
})
public class ApplicationControllerTest {

    @Autowired
    protected MockMvcOnVirtualThreads mockMvc;

    @Test
    void getHello() throws Exception {

        String expected = "true";

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/hello"))
                .andExpect(status().isOk()).andExpect(content().string(expected));

    }

}
