package com.homeoffice.projectmongo.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.homeoffice.projectmongo.domain.User;
import com.homeoffice.projectmongo.repository.UserRepository;


@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository; //injetando o UserRepository
	
	@Override
	public void run(String... args) throws Exception {
		
		userRepository.deleteAll(); //limpa a colecao no mongoDB
		
		User thassia = new User(null, "Thassia Reis", "minininha@gmail.com");
		User thuria = new User(null, "Thuria Reis", "mininazona@gmail.com");
		User cecilia = new User(null, "Cecilia Reis", "sibita@gmail.com");
		User juscelina = new User(null, "Juscelina Cortez", "maezinha@gmail.com");
		User thales = new User(null, "Thales Will", "mininozinho@gmail.com");
		User dayane = new User(null, "Dayane Faisca", "faisquinha@gmail.com");
		
		userRepository.saveAll(Arrays.asList(thassia, thuria, cecilia, juscelina, thales, dayane));
	}

}
