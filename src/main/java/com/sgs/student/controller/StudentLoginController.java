package com.sgs.student.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sgs.student.database.DatabaseConnector;


@RestController
@CrossOrigin(origins="http://localhost:3000/")
@RequestMapping("api/student")

public class StudentLoginController{
	@PostMapping("/login")
	public  HashMap<String,String> login(@RequestParam("register_no")String registerNo,@RequestParam("password")String password)throws SQLException
	{
		DatabaseConnector db = new DatabaseConnector();
		db.createConnection();
		try {
			
			
			 ResultSet result =db.getValue("SELECT * FROM student_login WHERE register_no='"+registerNo+"'");
			 while(result.next()) {
				 String get_password = result.getString("password");
				 String classgroup = result.getString("class_group");
				 
				 
				 if(password.equals(get_password))
				 {
					 HashMap<String,String> hash = new HashMap<String,String>();
					 hash.put("registerNo",registerNo);
					 hash.put("classGroup", classgroup);
					 hash.put("loginStatus","true");
					return hash;
				 } 
					 
					 
			 }
			 		
			
		}catch(RuntimeException e) {
			return null;
			
		}
		finally {
			db.closeConnection();
		}
		 HashMap<String,String> response = new HashMap<String,String>();
         response.put("loginStatus","false");
         return response;
			
		
		
	}
}
