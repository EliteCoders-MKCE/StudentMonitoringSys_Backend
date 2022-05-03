package com.sgs.student.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@CrossOrigin
/**
 * @class ViewController
 * @author simclair
 *	Maps the frontend with request
 */
public class ViewController {
	
	/**
	 * @method index()
	 * return type -  render index.html
	 * */
	 @RequestMapping(value = "/{[path:[^\\.]*}")
	 public String index()
	 {
		 return "index.html";
	 }
	 
	 @RequestMapping(value = "/stdreg")
	 public String registerStudent()
	 {
		 return "face.html";
	 }
	 
}
