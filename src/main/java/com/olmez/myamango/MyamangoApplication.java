package com.olmez.myamango;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.olmez.myamango.currency.CurrencyService;
import com.olmez.myamango.model.User;
import com.olmez.myamango.repositories.UserRepository;
import com.olmez.myamango.utility.UserRoles;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@AllArgsConstructor
@Slf4j
public class MyamangoApplication implements CommandLineRunner {

	private final UserRepository userRepository;
	private final CurrencyService currencyService;
	private final MongoTemplate mongoTemplate;

	public static void main(String[] args) {
		SpringApplication.run(MyamangoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		if (!isCollectionExist("user")) {
			createCollection("user");
		}

		List<User> users = userRepository.findAll();
		if (users.isEmpty()) {
			userRepository.save(UserRoles.createTempUser());
		}
		log.info("*Database connection is OK! {} users", users.size());
		log.info("**Mya mango application has started! * * *");
		currencyService.checkLastWeek();

	}

	private boolean isCollectionExist(String collectionName) {
		return mongoTemplate.collectionExists(collectionName);
	}

	private boolean createCollection(String name) {
		if (isCollectionExist(name)) {
			return false;
		}
		mongoTemplate.createCollection(name);
		return isCollectionExist(name);
	}

}
