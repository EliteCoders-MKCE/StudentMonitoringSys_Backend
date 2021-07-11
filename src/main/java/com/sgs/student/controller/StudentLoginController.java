package com.sgs.student.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sgs.student.exception.ResourceNotFoundException;
import com.sgs.student.interfaces.StudentLoginInterface;
import com.sgs.student.models.StudentLogin;

@RestController
@RequestMapping("api/student")
public class StudentLoginController {
	@Autowired
	private StudentLoginInterface studentLoginInterface;
	
	@GetMapping("all")
	public List <StudentLogin>getall()
	{
		return studentLoginInterface.findAll();
	}
	@GetMapping("{id}")
	public Optional <StudentLogin> GetById(@PathVariable(value="id")String StudentId)
	{
		return this.studentLoginInterface.findById(StudentId);
	}
	
	@PostMapping("login")
	public HashMap login (@RequestParam("register_no")String registerNo , @RequestParam("password")String password)
	{
	StudentLogin student = this.studentLoginInterface.findById(registerNo).orElseThrow(()->new ResourceNotFoundException("User Not Found"));
	if(password.equals(student.getPassword())&& registerNo == student.getRegisterNo())
	{
		 HashMap<String,String> response = new HashMap<String,String>();
         response.put("registerNo",student.getRegisterNo());
         response.put("class_group", student.getClassGroup());
         response.put("loginStatus","true");
         
         return response;
	}
         else
         {
        	 HashMap<String,String> response = new HashMap<String,String>();
             response.put("loginStatus","false");
             return response;
         }
         
	
	}
}