package com.sunny.Book.Library.System.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan("com.sunny.Book.Library.System")
@EnableJpaRepositories("com.sunny.Book.Library.System.repository")
public class AppConfig {
    //Configuration settings
}

