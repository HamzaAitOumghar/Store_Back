package com.store.dao;

import java.util.Set;
import com.store.security.User;
import com.store.security.UserRole;

public interface UserDao {
	
	
	User createUser(User user, Set<UserRole> userRoles);
	User findByUsername(String username);
	User findByEmail(String email);
	User save(User user);
	User findById(Long id);
	
	
	
	
	

}
