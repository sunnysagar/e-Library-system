package com.sunny.Book.Library.System;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Entity;

@SpringBootApplication
public class BookLibrarySystemApplication {

	public static void main(String[] args) {

		SpringApplication.run(BookLibrarySystemApplication.class, args);
	}

//	@RestController
//	public static class HomeController{
//
//		@RequestMapping("/")
//		public String index(){
//			return "redirect:/admin/signup";
//		}
//	}

}
