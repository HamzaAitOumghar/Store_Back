package com.store.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties.Env;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import com.store.security.User;

@Component
public class MailConstructor {

	@Autowired
	private Environment env;
	
	public SimpleMailMessage constructNewEmailUser(User user ,String password) {
		
		String message = "Bienvenue sur STORE DES LIVRES Voila votre compte/mot de passe pour s'authentifier : votre identifiant : "+user.getUsername() + " votre mot de passe : "+password ;
		
		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(user.getEmail());
		email.setSubject("STORES DES LIVRE - Nouveau Compte");
		email.setText(message);
		email.setFrom(env.getProperty("support.email"));
		return email;
	}
	
}
