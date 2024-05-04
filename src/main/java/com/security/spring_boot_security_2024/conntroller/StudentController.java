package com.security.spring_boot_security_2024.conntroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

	@GetMapping("/studentData")
	public String getStudentData() {
		return "I am student.........";
	}
}
