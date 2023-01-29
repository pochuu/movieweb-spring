package com.pochu.movieweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class MovieWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieWebApplication.class, args);
	}

}
