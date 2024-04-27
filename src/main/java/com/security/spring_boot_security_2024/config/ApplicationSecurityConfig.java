package com.security.spring_boot_security_2024.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.*;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
/**
 * 
 * @author Mo Masood Ansari
 * taken from spring-security-config dependency
	@EnableWebSecurity(debug = true) it will enable security filter chain as well as 
	enable to call @bean annotation automatically...
 *
 */
@EnableWebSecurity(debug = true)
public class ApplicationSecurityConfig {

	@Autowired
	private HttpSecurity httpSecurity;
	
	
	@Bean//means spring automatically call this method
	public InMemoryUserDetailsManager setUserSecurity() {
		
		UserDetails nazim = 
				User
				.withUsername("nazim") 
				.password("{noop}nazim@123")
				.roles("admin","user")
				.build();
		
		UserDetails priya = 
				User
				.withUsername("priya") 
				.password("{noop}priya@123")
				.roles("admin","user")
				.build();
		
		
		return new InMemoryUserDetailsManager(nazim,priya);
		
		// we can add user like this 
		
		/*GrantedAuthority user = new SimpleGrantedAuthority("user");
		GrantedAuthority admin = new SimpleGrantedAuthority("admin");
		
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(user);
		authorities.add(admin);
		
		UserDetails details = new User("Ansari", "Ansari@123",authorities);
		
		InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager();
		inMemoryUserDetailsManager.createUser(details);
		
		return inMemoryUserDetailsManager;*/
	}
	
	/**
	 * with the help of this method can enable security filter chain 
	 * and disable filter chain
	 * @return
	 * @throws Exception
	 */
	@Bean
	SecurityFilterChain settingSecurityFilterChain() throws Exception {
		
		/**
		 * this below line will protect the end points
		 * it will directly open the login page 
		 */
		//httpSecurity.authorizeHttpRequests().anyRequest().authenticated();
		
		/*
		 * how to authenticate the end points 
		 * here requestMatcher(String str) will take the string endpoints urls
		 * below code we can use in spring 6.0.0 version only...
		 * ****AntPathRequestMatcher.antMatcher("/") we can use this at place of antMatcher("/studentData")
		 */
		//httpSecurity.authorizeHttpRequests().requestMatchers(antMatcher("/studentData")).authenticated();
		
		/*
		 *It will deny this end points request 
		 *below code we can use in 6.0.0 version only....
		 */
		//httpSecurity.authorizeHttpRequests().requestMatchers(antMatcher("/employeeData")).denyAll();
		
		/*
		 * below line code for 6.0.0 and above version this is the right way to code
		 * to use below line of code we have to import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.*;
		 * security.requestMatchers(antMatcher("/employeeData")).authenticated();
		 */
		httpSecurity.authorizeHttpRequests(security->{
			security.requestMatchers(antMatcher("/employeeData")).authenticated();
			security.requestMatchers(antMatcher("/studentData"),antMatcher("/WEB-INF/view/**"),antMatcher("/register"))
			.permitAll()
			.anyRequest()
			.authenticated();
		});
		
		/**
		 * but below these two lines will enable security filter chain
		 * but if we delete them it will remove security filter chain
		 * but these codes won't protect any end points to protect individual endpoints
		 * we have to write some codes....
		 */
		httpSecurity.formLogin();
		httpSecurity.httpBasic();
		
		//this will disable security filter chain
		return httpSecurity.build();
	}
	
	
	/*
	 * to avoid exception in console
	 * spring security looking for this bean while matching our incoming urls..
	 * but we can comment this anyMatchers() in requestMatchers() method
	 * if we use below code 
	 * security.requestMatchers(antMatcher("/employeeData")).authenticated();
	 * then we can comment below method
	 */
//	@Bean(name = "mvcHandlerMappingIntrospector")
//	HandlerMappingIntrospector handlerMappingIntrospector() {
//		return new HandlerMappingIntrospector();
//	}
	
	//we can remove this 
	/**
	 * this means it will not encode password
	 * @return
	 */
	/*@Bean
	PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}*/
}
