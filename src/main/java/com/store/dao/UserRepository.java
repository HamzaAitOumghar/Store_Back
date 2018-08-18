package com.store.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.store.security.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	User findByUsername(String usernamer);
	User findByEmail(String email);
	List<User> findAll();

}
