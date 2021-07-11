package com.sgs.student.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{
	 private static final long serialVersionUId = 1L;
	    public ResourceNotFoundException(String message)
	    {
	        super(message);
	      System.out.println("Resource not found  - error 404");
	      
	    }


}
