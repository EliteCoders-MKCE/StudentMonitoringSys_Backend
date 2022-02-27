package com.sgs.student.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 
 * @author simclair
 * @class ErrorHandler
 * 	 Methods:
 * 			handleError
 * Used to map error page to the controller
 */
@Controller
public class ErrorHandler implements ErrorController  {
	/**
	 * @return error.html
	 */
    @RequestMapping("/error")
    public String handleError() {
        return "error.html";
    }
}