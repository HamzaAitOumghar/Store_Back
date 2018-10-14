package com.store.dao;

import com.store.entities.UserShipping;

public interface UserShippingDao {
	
	UserShipping findById(Long id);
	
	void removeById(Long id);


}
