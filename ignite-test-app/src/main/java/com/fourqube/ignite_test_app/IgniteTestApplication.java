package com.fourqube.ignite_test_app;

import org.apache.ignite.configuration.ClientConfiguration;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@SpringBootApplication
public class IgniteTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(IgniteTestApplication.class, args);
	}

	@Value("${ignite.addresses}")
	private String addresses;
	@Bean
	public ClientConfiguration clientConfiguration() {
		// If you provide a whole ClientConfiguration bean then configuration properties will not be used.
		ClientConfiguration cfg = new ClientConfiguration();
		cfg.setAddresses(addresses.split(","));
		return cfg;
	}
}
