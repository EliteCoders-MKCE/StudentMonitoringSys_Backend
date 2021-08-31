package com.sgs.student.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@CrossOrigin
public class ViewController {

	 @RequestMapping(value = "/{[path:[^\\.]*}")
	 public String index()
	 {
		 return "index.html";
	 }
	 
}
