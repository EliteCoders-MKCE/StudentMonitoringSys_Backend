package com.sgs.student.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ViewController {

	@RequestMapping("/")
	 public String index()
	 {
		 return "Hello World";
	 }
}
