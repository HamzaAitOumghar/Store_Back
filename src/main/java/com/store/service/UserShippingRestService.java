package com.store.service;

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
import com.store.dao.UserShippingDao;
import com.store.entities.UserBilling;
import com.store.entities.UserPayment;
import com.store.entities.UserShipping;
import com.store.security.User;

@RestController
@RequestMapping("/shipping")
public class UserShippingRestService {
	
	

	@Autowired
	private UserShippingDao repoShipping;
	
	@Autowired
	private UserDao repoUser;
	
	@RequestMapping(value="/add" , method=RequestMethod.POST)
	ResponseEntity addNewShippingPost(@RequestBody UserShipping userShipping , Principal principal) {
		
		User user =null;
		
		if(principal!=null) {
		  user = this.repoUser.findByUsername(principal.getName());
		}
		 
		this.repoUser.updateUserShipping(userShipping,user);
		
		return new ResponseEntity("Shipping Added " , HttpStatus.OK);
		
	}
	
	
	@RequestMapping(value="/remove" , method=RequestMethod.POST)
	ResponseEntity removeShippingPost(@RequestBody String id , Principal principal) {
		
		this.repoShipping.removeById(Long.parseLong(id));
		return new ResponseEntity("Shipping est bien supprimer  " , HttpStatus.OK);
		
		
		
	}
	
	@RequestMapping(value="/setDefault" , method=RequestMethod.POST)
	ResponseEntity setDefaultShippingPost(@RequestBody String id , Principal principal) {
		

		User user =null;
		
		if(principal!=null) {
		  user = this.repoUser.findByUsername(principal.getName());
		}
		
		this.repoUser.setUserDefaultShipping(Long.parseLong(id),user);
		return new ResponseEntity("Livraison par default est selectionner  !  " , HttpStatus.OK);
		
		
		
	}
	
	
	@RequestMapping("/getUserListShipping")
	List<UserShipping>  getUserShippingList(Principal principal) {
		

		User user =null;
		
		if(principal!=null) {
		  user = this.repoUser.findByUsername(principal.getName());
		}
		
		List<UserShipping> payments = user.getUserShippings();
		return payments;
		
		
	}
	
	

}
