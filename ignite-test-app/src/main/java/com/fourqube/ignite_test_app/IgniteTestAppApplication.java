package com.fourqube.ignite_test_app;

import org.apache.ignite.configuration.ClientConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@SpringBootApplication
public class IgniteTestAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(IgniteTestAppApplication.class, args);
	}

	@Bean
	@Primary
	public ClientConfiguration clientConfiguration() {
		// If you provide a whole ClientConfiguration bean then configuration properties will not be used.
		ClientConfiguration cfg = new ClientConfiguration();
		cfg.setAddresses("127.0.0.1:10801");
		return cfg;
	}
}
