package com.vscode.springbootjournalappmongodb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class JournalManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(JournalManagementApplication.class, args);
	}

}
