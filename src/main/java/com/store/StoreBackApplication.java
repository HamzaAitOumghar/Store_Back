package com.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.store.dao.UserRepository;
import com.store.security.SecurityUtility;
import com.store.security.User;

@SpringBootApplication
public class StoreBackApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepo ;
	
	public static void main(String[] args) {
		SpringApplication.run(StoreBackApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

//		User user = new User();
//		BCryptPasswordEncoder encoder = SecurityUtility.passwordEncoder();
//		
//		user.setUsername("j");
//		user.setPassword(encoder.encode("j"));
//		
//		this.userRepo.save(user);
		
	}
}
