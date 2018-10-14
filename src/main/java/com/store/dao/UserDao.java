package com.store.dao;

import java.util.Set;

import com.store.entities.UserBilling;
import com.store.entities.UserPayment;
import com.store.entities.UserShipping;
import com.store.security.User;
import com.store.security.UserRole;

public interface UserDao {
	
	
	User createUser(User user, Set<UserRole> userRoles);
	User findByUsername(String username);
	User findByEmail(String email);
	User save(User user);
	User findById(Long id);
	void updateUserPaymentInfo( UserBilling userBilling , UserPayment userPayment,User user );
	void updateUserBilling( UserBilling userBilling , UserPayment userPayment,User user );
	void setUserDefaultPayment(Long userPaymentId,User user) ;
	
	void updateUserShipping(UserShipping userShipping,User user);
	void setUserDefaultShipping(Long userShippingId,User user);
	

}
