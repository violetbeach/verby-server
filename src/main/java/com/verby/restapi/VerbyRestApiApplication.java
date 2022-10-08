package com.verby.restapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class VerbyRestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(VerbyRestApiApplication.class, args);
	}

}
