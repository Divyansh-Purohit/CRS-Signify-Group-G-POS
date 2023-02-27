package com.signify.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableAutoConfiguration
@ComponentScan("com.signify.*")
@EnableWebMvc
@SpringBootApplication
public class CrsSignifySpringRestGroupGApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrsSignifySpringRestGroupGApplication.class, args);
		System.out.println("application started on port 8080");
	}

}
