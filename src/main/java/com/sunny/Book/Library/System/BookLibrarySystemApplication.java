package com.sunny.Book.Library.System;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.Entity;

@SpringBootApplication
public class BookLibrarySystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookLibrarySystemApplication.class, args);
	}

}
