package com.store.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.entities.UserShipping;

@Service
public class UserShippingDaoImp implements UserShippingDao {
	
	@Autowired
	private UserShippingRepository repo;

	@Override
	public UserShipping findById(Long id) {
	
		return repo.findById(id).get();
	}

	@Override
	public void removeById(Long id) {
		this.repo.deleteById(id);
	}

}
