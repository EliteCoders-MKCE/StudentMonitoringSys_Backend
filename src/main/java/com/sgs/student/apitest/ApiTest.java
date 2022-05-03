package com.sgs.student.apitest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sgs.student.controller.Mailer;

@RestController
@CrossOrigin
@RequestMapping("/api/test")
public class ApiTest {
	@GetMapping("/mail")
	public String testMail() {
		
			System.out.println("Testing Mail service");
			Mailer mailer = new Mailer();
			mailer.sendMail("simclair.sgs@gmail.com","Mailer Test","This is a test message from Api-Test");
			//Mailer.send("elite.alertsys@rediffmail.com", "Sgr@1234","simclair.sgs@gmail.com", "Mailer Test","This is a test message from Api-Test");
			return "Mail Sent";
		
			//return "Mailer Failed";
	}

}
