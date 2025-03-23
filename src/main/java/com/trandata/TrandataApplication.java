package com.trandata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class TrandataApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrandataApplication.class, args);
	}

}
