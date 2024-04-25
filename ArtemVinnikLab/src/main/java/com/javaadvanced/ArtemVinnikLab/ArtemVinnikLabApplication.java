package com.javaadvanced.ArtemVinnikLab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "com.example.demo.controllers", "com.example.demo.configs" })
public class ArtemVinnikLabApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArtemVinnikLabApplication.class, args);
	}

}
