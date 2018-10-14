package com.store.service;

import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.store.dao.UserDao;
import com.store.dao.UserPaymenDao;
import com.store.entities.UserBilling;
import com.store.entities.UserPayment;
import com.store.security.User;

@RestController
@RequestMapping("/payment")
public class UserPaymentRestService {
	
	@Autowired
	private UserPaymenDao repoPayment;
	
	@Autowired
	private UserDao repoUser;
	
	@RequestMapping(value="/add" , method=RequestMethod.POST)
	ResponseEntity addNewCreditCardPost(@RequestBody UserPayment userPayment , Principal principal) {
		
		User user =null;
		
		if(principal!=null) {
		  user = this.repoUser.findByUsername(principal.getName());
		}
		 
		UserBilling userBilling =userPayment.getUserBilling();
		this.repoUser.updateUserBilling(userBilling,userPayment,user);
		
		return new ResponseEntity("Payment Added " , HttpStatus.OK);
		
		
		
	}
	
	@RequestMapping(value="/remove" , method=RequestMethod.POST)
	ResponseEntity removePaymentPost(@RequestBody String id , Principal principal) {
		
		this.repoPayment.removeById(Long.parseLong(id));
		return new ResponseEntity("Payment est bien supprimer  " , HttpStatus.OK);
		
		
		
	}
	
	
	@RequestMapping(value="/setDefault" , method=RequestMethod.POST)
	ResponseEntity setDefaultPaymentPost(@RequestBody String id , Principal principal) {
		

		User user =null;
		
		if(principal!=null) {
		  user = this.repoUser.findByUsername(principal.getName());
		}
		
		this.repoUser.setUserDefaultPayment(Long.parseLong(id),user);
		return new ResponseEntity("Payment par default est selectionner  !  " , HttpStatus.OK);
		
		
		
	}
	
	@RequestMapping("/getUserPaymentList")
	List<UserPayment>  getUserPaymentList(Principal principal) {
		

		User user =null;
		
		if(principal!=null) {
		  user = this.repoUser.findByUsername(principal.getName());
		}
		
		List<UserPayment> payments = user.getUserPayments();
		return payments;
		
		
	}
	

}
