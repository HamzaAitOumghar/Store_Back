package com.store.service;

import java.security.Principal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.store.dao.UserDao;
import com.store.dao.UserDaoImp;
import com.store.dao.UserRepository;
import com.store.resource.MailConstructor;
import com.store.security.Role;
import com.store.security.SecurityConfig;
import com.store.security.SecurityUtility;
import com.store.security.User;
import com.store.security.UserRole;

@RestController
@RequestMapping("/user")
public class UserRestService {

	@Autowired
	private UserDao repo ;

	@Autowired
	private MailConstructor mailConstructor;

	@Autowired
	private JavaMailSender mailSender;

	@RequestMapping(value="/newUser",method=RequestMethod.POST)
	public ResponseEntity newUserPost(HttpServletRequest request , @RequestBody HashMap<String, String> map) {

		String username = map.get("username");
		String email = map.get("email");




		if(this.repo.findByUsername(username)!=null) {
			return new ResponseEntity("Username deja Existe ! !",HttpStatus.BAD_REQUEST);
		}
		if(this.repo.findByEmail(email)!=null) {
			return new ResponseEntity("Email deja Existe ! !",HttpStatus.BAD_REQUEST);
		}

		User user =new User();
		user.setUsername(username);
		user.setEmail(email);

		String password=SecurityUtility.randomPassword();
		user.setPassword(SecurityUtility.passwordEncoder().encode(password));

		Role role = new Role();
		role.setRoleId(2);
		role.setName("ROLE_USER");

		Set<UserRole> userRoles=new HashSet<>();

		UserRole userRole = new UserRole();

		userRole.setUser(user);
		userRole.setRole(role);

		userRoles.add(userRole);

		this.repo.createUser(user, userRoles);

		SimpleMailMessage emailMessage=mailConstructor.constructNewEmailUser(user,password);
		mailSender.send(emailMessage);

		return new ResponseEntity("User Added Successfully",HttpStatus.OK);


	}


	@RequestMapping(value="/forgetPassword",method=RequestMethod.POST)
	public ResponseEntity forgetPassword(HttpServletRequest request , @RequestBody   HashMap<String, String> map) {


		String email = map.get("email");
		System.out.println(email);

		User user = this.repo.findByEmail(email);


		if(user==null) {
			return new ResponseEntity("Email n'existe pas !",HttpStatus.BAD_REQUEST);
		}

		String password=SecurityUtility.randomPassword();
		user.setPassword(SecurityUtility.passwordEncoder().encode(password));
		this.repo.save(user);

		SimpleMailMessage emailMessage=mailConstructor.constructNewEmailUser(user,password);
		mailSender.send(emailMessage);

		return new ResponseEntity("Email Sent",HttpStatus.OK);


	}

	@RequestMapping(value="/updateUserInfo",method=RequestMethod.POST)
	public ResponseEntity updateUserInfo( @RequestBody   HashMap<String, Object> map) throws Exception  {
		
		int id =(Integer)map.get("id");
		String email = (String)map.get("email");
		String username = (String)map.get("username");
		String firstName = (String)map.get("firstName");
		String lastName = (String)map.get("lastName");
		String newPassword = (String)map.get("newPassword");
		String currentPassword = (String)map.get("password");


		User currentUser = this.repo.findById((long)id);

		if(currentUser==null) {
			throw new Exception("utilisateur non trouvé ! !");
		}

		if(this.repo.findByEmail(email)!=null) {

			if(this.repo.findByEmail(email).getId()!=currentUser.getId()) {
				return new ResponseEntity("Email non trouvé !!",HttpStatus.BAD_REQUEST);
			}
		}

		if(this.repo.findByUsername(username)!=null) {

			if(this.repo.findByUsername(username).getId()!=currentUser.getId()) {
				return new ResponseEntity("Utilisateur non trouvé !!",HttpStatus.BAD_REQUEST);
			}
		}


		
		BCryptPasswordEncoder encoder = SecurityUtility.passwordEncoder();

		String dbpassword = currentUser.getPassword();

		if(encoder.matches(currentPassword,dbpassword)) {
			currentUser.setPassword(encoder.encode(newPassword));
			
		}else {
			return new ResponseEntity("le mot de passe actuel est incorect  !!",HttpStatus.BAD_REQUEST);

		}
		
		currentUser.setFirstName(firstName);
		currentUser.setLastName(lastName);
		currentUser.setUsername(username);
		currentUser.setEmail(email);

		repo.save(currentUser);
		
		return new ResponseEntity("Mise a jour effectuer avec success !!",HttpStatus.OK);

	}

	@RequestMapping("/getCurrentUser")
	public User getCurrentUser(Principal principal ) {

		User user=new User();
		
		if(principal!=null) {
			user=this.repo.findByUsername(principal.getName());
			
		}
		
		return user;
		
	}

}
