package com.store.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.store.dao.UserRepository;
import com.store.security.User;

@Service
public class UserSecurityService implements UserDetailsService {

	private static final Logger LOG = LoggerFactory.getLogger(UserSecurityService.class);
	
	@Autowired
	private UserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String arg0) throws UsernameNotFoundException {

		User user = this.userRepo.findByUsername(arg0);
		
		if(null==user) {
			LOG.warn("USERNAME {} not found ",arg0);
			throw new UsernameNotFoundException("cannot find username : "+arg0);
			
		}
		return user;
		
		
		
	}

	
}
