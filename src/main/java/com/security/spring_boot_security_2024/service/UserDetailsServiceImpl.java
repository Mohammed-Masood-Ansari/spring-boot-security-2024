package com.security.spring_boot_security_2024.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.security.spring_boot_security_2024.dto.Student;

/**
 * 
 * @author Mohammad Masood Ansari
 * 
 * this class is used to fetch user details from database for login
 * we use for login user name and password and check role as well
 * 
 *
 */

public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//how to load user from database
		String query  = "SELECT * FROM student where name=?";
		List<Student> student=jdbcTemplate.query(query, new BeanPropertyRowMapper<>(Student.class),username);
		if(student.size()==0) {
			throw new RuntimeException("user doesn't exist");
		}
		UserDetails userDetails=User.withUsername(student.get(0).getName())
				.password(student.get(0).getPassword())
				.roles(student.get(0).getRole())
				.build();
		
		return userDetails;
	}

}
