package com.example.demo;

import com.example.demo.entity.DBUser;
import com.example.demo.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.demo")
@EnableJpaRepositories(basePackages = "com.example.demo.repository")
public class DemoApplication {

	private static final Logger log = LoggerFactory.getLogger(DemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	/*@Autowired UserRepository userRepository;

	@PostConstruct
	void checkitOut() {
		userRepository.save(new DBUser("user_Account1@example.com", "password1", "Doe", "John", "USER"));
		userRepository.save(new DBUser("user_Account2@example.com", "password2", "Smith", "Jane", "USER"));
	}

	for (DBUser user : userRepository.findAll()) {
		log.info("Hello" + user.toString());
	}*/
}
