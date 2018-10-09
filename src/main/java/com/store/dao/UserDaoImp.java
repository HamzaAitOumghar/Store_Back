package com.store.dao;

import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.security.Role;
import com.store.security.User;
import com.store.security.UserRole;

@Service
public class UserDaoImp implements UserDao{

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private RoleRepository roleRepo;


	@Transactional
	public User createUser(User user, Set<UserRole> userRoles) {

		User localUser=userRepo.findByUsername(user.getUsername());

		if(localUser!=null) {
			System.out.println("Utilisateur deja existe ! !");
		}else {

			for(UserRole role : userRoles) {
				roleRepo.save(role.getRole());
			}

			user.getUserRole().addAll(userRoles);
			localUser=userRepo.save(user);

		}

		return user;
	}

	@Override
	public User findByUsername(String username) {
		return this.userRepo.findByUsername(username);
	}

	@Override
	public User findByEmail(String email) {
		
		return this.userRepo.findByEmail(email);
	}

	@Override
	public User save(User user) {
		return this.userRepo.save(user);
	}

	@Override
	public User findById(Long id) {
		return this.userRepo.findById(id).get();
	}

}
