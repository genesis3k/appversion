package com.se.tech.versiondemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController

public class VersiondemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(VersiondemoApplication.class, args);
	}
	
	@RequestMapping(value = "/")
	 public String hello() {
	    return "Hello World";
	 }
	
}