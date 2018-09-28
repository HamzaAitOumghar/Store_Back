package com.store.resource;

import java.util.Collections;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.store.service.UserSecurityService;


@RestController
public class LoginResource {
	
	@Autowired
	private UserSecurityService service;
	
	@RequestMapping("/token")
	public Map<String, String> token(HttpSession session,HttpServletRequest request){
	
		String remoteHost=request.getRemoteHost();
		int portNumber=request.getRemotePort();	
		return Collections.singletonMap("token",session.getId());
	}
	
	@RequestMapping("/checkSession")
	 public ResponseEntity<String> checkSession() {
		
		return new ResponseEntity<String>("Session Active",HttpStatus.OK);
		
	}
	
	
	@RequestMapping(value="/user/logout",method=RequestMethod.POST)
	public ResponseEntity<String>  logout() {
		SecurityContextHolder.clearContext();
		return  new ResponseEntity<String>("logout successfully !",HttpStatus.OK);
	}
	

}
