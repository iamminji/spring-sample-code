package com.example.javaspringevent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class JavaSpringEventApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaSpringEventApplication.class, args);
    }

}
