package com.example.bookstoreapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BookStoreAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookStoreAppApplication.class, args);
	}

}
