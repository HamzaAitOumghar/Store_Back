package com.store.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.entities.UserBilling;
import com.store.entities.UserPayment;
import com.store.entities.UserShipping;
import com.store.security.Role;
import com.store.security.User;
import com.store.security.UserRole;

@Service
public class UserDaoImp implements UserDao{

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private RoleRepository roleRepo;
	@Autowired
	private UserPaymentRepository paymentRepo;
	@Autowired
	private UserBillingRepository billingRepo;
	@Autowired
	private UserShippingRepository shippingRepo;
	


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
			user.setUserPayments(new ArrayList<UserPayment>());
			
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
	
	@Override
	public void updateUserPaymentInfo(UserBilling userBilling , UserPayment userPayment,User user ) {
		save(user);
		this.paymentRepo.save(userPayment);
		this.billingRepo.save(userBilling);
		
	}

	@Override
	public void updateUserBilling(UserBilling userBilling, UserPayment userPayment, User user) {
		userPayment.setUser(user);
		userPayment.setUserBilling(userBilling);
		userPayment.setDefaultPayment(true);
		userBilling.setUserPayment(userPayment);
		user.getUserPayments().add(userPayment);
		
		save(user);
		
		
		
		
	}
	
	
	@Override
	public void setUserDefaultPayment(Long userPaymentId,User user) {
		List<UserPayment> userPayments = paymentRepo.findAll();
		System.out.println();
		for(UserPayment pay : userPayments) {
			if(pay.getId()==userPaymentId) {
				pay.setDefaultPayment(true);
				paymentRepo.save(pay);
			}else {
				pay.setDefaultPayment(false);
				paymentRepo.save(pay);
			}
		}
		
	}

	@Override
	public void updateUserShipping(UserShipping userShipping, User user) {
		
		userShipping.setUser(user);
		userShipping.setUserShippingDefault(true);
		user.getUserShippings().add(userShipping);		
		save(user);

	}

	@Override
	public void setUserDefaultShipping(Long userShippingId, User user) {
		List<UserShipping> userShippings = shippingRepo.findAll();
		
		System.out.println("-------> Test " + userShippingId);
		for(UserShipping pay : userShippings) {
			if(pay.getId()==userShippingId) {
				pay.setUserShippingDefault(true);
				shippingRepo.save(pay);
			}else {
				pay.setUserShippingDefault(false);
				shippingRepo.save(pay);
			}
		}
		
		
	}
	
	
}