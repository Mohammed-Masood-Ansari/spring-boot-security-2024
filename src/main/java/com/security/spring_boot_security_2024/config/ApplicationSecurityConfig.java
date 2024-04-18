package com.security.spring_boot_security_2024.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
//taken from spring-security-config dependency
@EnableWebSecurity(debug = true)
public class ApplicationSecurityConfig {

	@Bean//means spring automatically call this method
	public InMemoryUserDetailsManager setUserSecurity() {
		
		GrantedAuthority user = new SimpleGrantedAuthority("user");
		GrantedAuthority admin = new SimpleGrantedAuthority("admin");
		
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(user);
		authorities.add(admin);
		
		UserDetails details = new User("Ansari", "Ansari@123",authorities);
		
		InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager();
		inMemoryUserDetailsManager.createUser(details);
		
		return inMemoryUserDetailsManager;
	}
	
	/**
	 * this means it will not encode password
	 * @return
	 */
	@Bean
	PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
}
