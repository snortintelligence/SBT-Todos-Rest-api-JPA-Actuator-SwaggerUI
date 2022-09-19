package com.snort.intelli.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import lombok.extern.slf4j.Slf4j;

@EnableWebMvc
@SpringBootApplication
@Slf4j
public class TodosRestApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(TodosRestApiApplication.class, args);
		log.info("Todos-rest-api running fine...");
	}

}
