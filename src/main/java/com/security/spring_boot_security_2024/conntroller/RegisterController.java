package com.security.spring_boot_security_2024.conntroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegisterController {

	@GetMapping("/register")
	public String openRegisterPage() {
		return "registration";
	}
}
