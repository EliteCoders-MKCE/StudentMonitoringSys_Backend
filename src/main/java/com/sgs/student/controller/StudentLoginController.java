package com.sgs.student.controller;
import java.net.PasswordAuthentication;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Properties;
import javax.websocket.Session;

import org.springframework.boot.rsocket.server.RSocketServer.Transport;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sgs.student.database.DatabaseConnector;

@RestController
@CrossOrigin
@RequestMapping("api/student")



public class StudentLoginController {
	
	// CHANGE PASSWORD METHOD - POST /reset
	
	// FORGOT PASSWORD METHOD - POST /forgot
	
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
	
	@PostMapping("/changepassword")
	public String changePassword(@RequestParam("register_no")String registerNo ,@RequestParam("old_password")String old_password,@RequestParam("new_password")String newPassword)throws SQLException
	{
		DatabaseConnector db = new DatabaseConnector();
		db.createConnection();
		try {
			ResultSet result = db.getValue("SELECT password FROM student_login WHERE register_no='"+registerNo+"'");
			
			while(result.next())
			{
				String get_password = result.getString("password");
				if(old_password.equals(get_password))
				{
					db.query("UPDATE student_login SET password='"+newPassword+"' WHERE register_no='"+registerNo+"'");
				return "Password Change Successfully";
				
				
					
				}
			}
		}catch(RuntimeException e) {return null;}
		finally {db.closeConnection();}
		return "Failed";
	}

/*@PostMapping("/forgotpassword")
public String forgotPassword(@RequestParam("register_no")String registerNo)throws SQLException
{
	DatabaseConnector db = new DatabaseConnector();
	db.createConnection();
	try {
		db.query("SELECT student_login SET password=")
	}
	return "ok";
}*/
@PostMapping("/forgotpassword")
public void forgotPassword() {
	String to="sasikumarnsk6@gmail.com";
	String from ="simclair.sgs@gmail.com";
	String host="smtp.gmail.com";
	 Properties properties = System.getProperties();
     properties.put("mail.smtp.host", host);
     properties.put("mail.smtp.port", "465");
     properties.put("mail.smtp.ssl.enable", "true");
     properties.put("mail.smtp.auth", "true");
     
}


}
