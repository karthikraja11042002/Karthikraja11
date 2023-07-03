package com.exterro.feedbackquestion.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {

	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

	@GetMapping("admin")
	public String showLoginForm() {
		logger.info("accessing the showLoginForm method");
		return "login.html";
	}

	@PostMapping("admin/signIn")
	public String processLoginForm(@RequestParam("username") String username,
			@RequestParam("password") String password) {

		logger.info("accessing the processLoginForm method");
		if (username.equals("admin") && password.equals("admin123")) {
			return "feedbackadded.html";
		} else {
			return "login.html";
		}
	}
}
