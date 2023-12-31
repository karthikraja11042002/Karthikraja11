package com.exterro.feedbackquestion.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	

	@RequestMapping("")
	public String getIndex() {
		logger.info("accesing the home page");
		return "home.html";
	}
	
	

}
