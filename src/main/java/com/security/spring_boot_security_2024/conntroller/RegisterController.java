package com.security.spring_boot_security_2024.conntroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.security.spring_boot_security_2024.dto.UserRegistration;

@Controller
public class RegisterController {

//	@Autowired
//	InMemoryUserDetailsManager detailsManager;
	
	/*
	 * below line of code will help the programmer to save user data inside database;
	 * byusing spring security...
	 */
	@Autowired
	JdbcUserDetailsManager jdbcUserDetailsManager;
	
	/*
	 * above code we can write like this as well....
	 * with the help of constructor injection..... 
	 */
//	public RegisterController(InMemoryUserDetailsManager detailsManager) {
//		this.detailsManager = detailsManager;
//	}
	
	@GetMapping("/register")
	public String openRegisterPage(@ModelAttribute("registeration") UserRegistration registration) {
		return "registration";
	}
	

	@ResponseBody
	@PostMapping("/userRegister")
	public String userRegister(UserRegistration user) {
		
		/*
		 * to store username and password in spring security we have to use below code..
		 * to store username and password in server we can use file,database,localMemory
		 */
		UserDetails userDetails = User
				.withUsername(user.getUserName())
				.password("{noop}"+user.getUserPassword())
				.roles("user","admin")
				.build();
		
		/*
		 * 
		 * below code will save the data for permanently inside database....
		 * 
		 */
		
		jdbcUserDetailsManager.createUser(userDetails);
		
		/*
		 * below line will save the data for some time
		 */
		//detailsManager.createUser(userDetails);
		
		return "user registered successfully....";
	}
}
