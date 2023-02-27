package com.signify.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CrsSignifySpringRestGroupGApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrsSignifySpringRestGroupGApplication.class, args);
		System.out.println("application started on port 8080");
	}

}
