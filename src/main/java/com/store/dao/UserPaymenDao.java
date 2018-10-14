package com.store.dao;

import com.store.entities.UserPayment;

public interface UserPaymenDao {
	
	UserPayment findById(Long id);
	
	void removeById(Long id);

}
