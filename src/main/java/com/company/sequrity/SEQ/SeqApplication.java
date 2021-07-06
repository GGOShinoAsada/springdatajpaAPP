package com.company.sequrity.SEQ;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EnableJpaRepositories("com.company.sequrity.SEQ.dao")
public class SeqApplication {

	public static void main(String[] args) {
		SpringApplication.run(SeqApplication.class, args);
	}

}
