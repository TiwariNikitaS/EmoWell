package com.app.emowell;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.app.repository")
@EntityScan("com.app.entity")
@ComponentScan("com.app")
public class EmoWellApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmoWellApiApplication.class, args);
	}

}
