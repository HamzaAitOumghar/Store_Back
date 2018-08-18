package com.store.security;

import java.io.Serializable;

import org.springframework.security.core.GrantedAuthority;

public class Authority implements GrantedAuthority,Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9155454483079021746L;
	private final String authority;
	
	public Authority(String authority) {
		this.authority=authority;
	}
	
	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return this.authority;
	}

}
