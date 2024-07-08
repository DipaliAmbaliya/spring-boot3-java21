package com.example.controller;

import com.example.repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.metrics.CompositeMeterRegistryAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.metrics.MetricsAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA;

@ExtendWith(SpringExtension.class)
@WebFluxTest
@ContextConfiguration(classes = PostRepository.class)
@Import({MetricsAutoConfiguration.class, CompositeMeterRegistryAutoConfiguration.class, WebFluxSecurityConfig.class})
@TestPropertySource(locations = {
        "classpath:/application-test.yml"
})
public class FileUploadControllerTest {

  @Autowired
  WebTestClient client;

  @MockBean
  FileUploadController fileUploadController;

  @Test
  public void testHandleSimpleFileUpload() {
    MultipartBodyBuilder multipartBodyBuilder = new MultipartBodyBuilder();
    multipartBodyBuilder.part("name", "test");
    multipartBodyBuilder.part("file", new ClassPathResource("spring.png"), MediaType.IMAGE_PNG);
    var multipartBody = multipartBodyBuilder.build();
    this.client
        .post().uri("/simple-form-upload")
        .contentType(MULTIPART_FORM_DATA)
        .body(BodyInserters.fromMultipartData(multipartBody))
        .exchange()
        .expectStatus().isOk();
  }
}