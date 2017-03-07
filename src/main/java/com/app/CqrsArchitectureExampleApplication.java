package com.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.cqrs","com.app"})
public class CqrsArchitectureExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(CqrsArchitectureExampleApplication.class, args);
	}
}
