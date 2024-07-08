package com.example.controller;

import com.example.model.FileUploadCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.http.codec.multipart.FilePartEvent;
import org.springframework.http.codec.multipart.FormPartEvent;
import org.springframework.http.codec.multipart.Part;
import org.springframework.http.codec.multipart.PartEvent;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Map;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@Slf4j
@SuppressWarnings("unused")
public class FileUploadController {

  @PostMapping(value = "simple-form-upload", consumes = MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<Map<String, String>> handleFileUploadForm(FileUploadCommand form) {

    log.info("uploading form data: {}", form);

    var result = Map.of(
        "name", form.getName(),
        "filename", form.getFile().filename()
    );
    return ok().body(result);
  }
}
