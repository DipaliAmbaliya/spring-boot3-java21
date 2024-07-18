package com.example;

import com.example.config.observation.SimpleLoggingHandler;
import com.example.model.Post;
import com.example.repository.PostRepository;
import com.example.service.JsonPlaceholderService;
import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import io.micrometer.observation.ObservationTextPublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.reactive.function.client.WebClientSsl;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
@Slf4j
public class Application {

	@Autowired
	JsonPlaceholderService jsonPlaceholderService;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(PostRepository postRepository, ObservationRegistry observationRegistry) {
		return args -> {
			// register observation handlers and simpleLoggingObservation handler
			observationRegistry
					.observationConfig()
					.observationHandler(new SimpleLoggingHandler())
					.observationHandler(new ObservationTextPublisher(log::info));
			// make an observation
			List<Post> posts = Observation
					.createNotStarted("json-place-holder.load-posts", observationRegistry)
					.lowCardinalityKeyValue("some-value", "100")
					.observe(jsonPlaceholderService::loadPosts);

			Observation
					.createNotStarted("post-repository.save-all",observationRegistry)
					.observe(() -> postRepository.saveAll(posts));
		};
	}

}
