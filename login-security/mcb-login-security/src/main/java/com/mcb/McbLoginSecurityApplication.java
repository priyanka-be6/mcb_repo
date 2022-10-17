package com.mcb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class McbLoginSecurityApplication {

	public static void main(String[] args) {		
		SpringApplication.run(McbLoginSecurityApplication.class, args);
	}

}
