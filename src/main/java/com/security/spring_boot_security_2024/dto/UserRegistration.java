package com.security.spring_boot_security_2024.dto;


public class UserRegistration {

	private String userName;
	private String userPassword;
	
	public UserRegistration() {
		super();
	}

	public UserRegistration(String userName, String userPassword) {
		super();
		this.userName = userName;
		this.userPassword = userPassword;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
}
