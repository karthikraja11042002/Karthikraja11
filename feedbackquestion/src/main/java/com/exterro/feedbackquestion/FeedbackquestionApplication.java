package com.exterro.feedbackquestion;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FeedbackquestionApplication {
	
	 private static final Logger logger = LoggerFactory.getLogger(FeedbackquestionApplication.class);

	public static void main(String[] args) {
		logger.info("In Main");
		SpringApplication.run(FeedbackquestionApplication.class, args);
	}

}
