package com.store.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.entities.UserPayment;

@Service
public class UserPaymentDaoImp  implements UserPaymenDao{

	
	@Autowired
	private UserPaymentRepository repo;
	
	@Override
	public UserPayment findById(Long id) {
		// TODO Auto-generated method stub
		return this.repo.findById(id).get();
	}

	@Override
	public void removeById(Long id) {
		 this.repo.deleteById(id);
		
	}

	
	
}
