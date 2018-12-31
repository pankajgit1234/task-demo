package com.glaucus.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TaskMainApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskMainApplication.class, args);
	}
}
