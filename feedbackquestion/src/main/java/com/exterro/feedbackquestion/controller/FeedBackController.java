package com.exterro.feedbackquestion.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.exterro.feedbackquestion.entity.FeedBackEntity;
import com.exterro.feedbackquestion.entity.UserEntity;
import com.exterro.feedbackquestion.request.UserRequest;
import com.exterro.feedbackquestion.services.FeedBackServices;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class FeedBackController {

	private static final Logger logger = LoggerFactory.getLogger(FeedBackController.class);

	@Autowired
	private FeedBackServices feedBackServices;

	@PostMapping(path = "/submitFeedback")
	@ResponseBody
	public ResponseEntity<String> submitFeedback(@RequestParam String userData)
			throws JsonMappingException, JsonProcessingException {
		logger.info("Accessing submitFeedback method");
		ObjectMapper objmap = new ObjectMapper();
		UserRequest user = objmap.readValue(userData, UserRequest.class);
		FeedBackEntity feedback = new FeedBackEntity(
				new UserEntity(user.getUserName(), user.getUserAge(), user.getUserEmail()), user.getAnswer1(),
				user.getAnswer2(), user.getAnswer3(), user.getAnswer4(), user.getAnswer5());
		feedBackServices.addFeedBack(feedback);

		return ResponseEntity.ok("registrationsuccessfull.html");
	}

	@GetMapping("admin/viewFeedback")
	@ResponseBody
	public String viewFeedBack() {
		logger.info("Accessing viewFeedback method");

		StringBuilder feedBack = new StringBuilder();
		feedBack.append("<h1>View all answers</h1>");
		feedBack.append("<table border='1px'>");
		feedBack.append(
				"<tr><th>UserName</th><th>Answer 1</th><th>Answer 2</th><th>Answer 3</th><th>Answer 4</th><th>Answer 5</th></tr>");

		try {
			for (FeedBackEntity feedback : feedBackServices.viewAllFeedBackDetails()) {
				feedBack.append("<tr>");
				feedBack.append("<td>").append(feedback.getUserEmail().getUserName()).append("</td>");
				feedBack.append("<td>").append(feedback.getAnswer1()).append("</td>");
				feedBack.append("<td>").append(feedback.getAnswer2()).append("</td>");
				feedBack.append("<td>").append(feedback.getAnswer3()).append("</td>");
				feedBack.append("<td>").append(feedback.getAnswer4()).append("</td>");
				feedBack.append("<td>").append(feedback.getAnswer5()).append("</td>");
				feedBack.append("</tr>");
			}
		} catch (Exception e) {
			logger.error("An error occurred while retrieving feedback details", e);
		}

		feedBack.append("</table>");
		return feedBack.toString();
	}

	@GetMapping("admin/viewFeedbackbyEmail")
	@ResponseBody
	public String viewFeedbackByEmail(@RequestParam String userEmail) {
		logger.info("Accessing viewFeedbackByEmail method");

		StringBuilder feedBack = new StringBuilder();
		feedBack.append("<h1>View answers by email</h1>");
		feedBack.append("<table border='1px'>");
		feedBack.append(
				"<tr><th>UserName</th><th>Answer 1</th><th>Answer 2</th><th>Answer 3</th><th>Answer 4</th><th>Answer 5</th></tr>");

		try {
			FeedBackEntity user = feedBackServices.viewFeedBack(userEmail);

			feedBack.append("<tr>");
			feedBack.append("<td>").append(user.getUserEmail().getUserName()).append("</td>");
			feedBack.append("<td>").append(user.getAnswer1()).append("</td>");
			feedBack.append("<td>").append(user.getAnswer2()).append("</td>");
			feedBack.append("<td>").append(user.getAnswer3()).append("</td>");
			feedBack.append("<td>").append(user.getAnswer4()).append("</td>");
			feedBack.append("<td>").append(user.getAnswer5()).append("</td>");
			feedBack.append("</tr>");
		} catch (Exception e) {
			logger.error("An error occurred while retrieving feedback details by email", e);
		}

		feedBack.append("</table>");
		return feedBack.toString();
	}
}
