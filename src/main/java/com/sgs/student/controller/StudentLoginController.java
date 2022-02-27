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
/**
 * 
 * @author simclair
 * @class StudentLoginController
 * 
 * Manages the student login, includes forgot and change password functionality
 */
@RestController
@CrossOrigin
@RequestMapping("api/student")
public class StudentLoginController {
		
	/**
	 * @static_method  getAlphaNumericString
	 * @param n
	 * @return String
	 */
	  static String getAlphaNumericString(int n)
	    {
	        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
	                                    + "0123456789"
	                                    + "abcdefghijklmnopqrstuvxyz";
	  
	        StringBuilder sb = new StringBuilder(n);
	        for (int i = 0; i < n; i++) {
	            int index
	                = (int)(AlphaNumericString.length()
	                        * Math.random());
	            sb.append(AlphaNumericString
	                          .charAt(index));
	        }
	  
	        return sb.toString();
	    }
	  

	
	// CHANGE PASSWORD METHOD - POST /reset
	 /**
	  * 
	  * @param registerNo
	  * @param oldPass
	  * @param newPass
	  * @return String
	  * @throws SQLException
	  */
	@PostMapping("/reset-pass")
	public String resetPass(@RequestParam("register_no")String registerNo,@RequestParam("old_pass")String oldPass,@RequestParam("new_pass")String newPass)throws SQLException
	{
		DatabaseConnector db = new DatabaseConnector();
		db.createConnection();
		try {
			
			
			 ResultSet result =db.getValue("SELECT * FROM student_login WHERE register_no='"+registerNo+"'");
			 while(result.next()) {
				 String get_password = result.getString("password");
				 if(oldPass.equals(get_password))
				 {
					 db.query("UPDATE student_login SET password='"+newPass+"' WHERE register_no='"+registerNo+"'");
					 return "Password Changed Successfully..";
				 }
				 else
				 return "Wrong old password...";
			 }
			 		
			
		}catch(RuntimeException e) {
			return null;
			
		}
		finally {
			db.closeConnection();
		}
		
		return "Uncaught Exception";
	}
	
	// FORGOT PASSWORD METHOD - POST /forgot
	/**
	 * 
	 * @param classGroup
	 * @param registerNo
	 * @param mailId
	 * @return
	 * @throws SQLException
	 */
	@PostMapping("/forgot-pass")
	public String forgotPassword(@RequestParam("class_group")String classGroup,@RequestParam("register_no")String registerNo,@RequestParam("e_mail")String mailId) throws SQLException
	{
		DatabaseConnector db = new DatabaseConnector();
		db.createConnection();
		try {
			
			
			 ResultSet result =db.getValue("SELECT * FROM "+classGroup+"_studentdetails WHERE register_no='"+registerNo+"'");
			 while(result.next()) {
				 String get_mail = result.getString("e_mail");
				 String get_name = result.getString("name");
				 if(mailId.equals(get_mail))
				 {
					 String newPass = StudentLoginController.getAlphaNumericString(8);
					 db.query("UPDATE student_login SET password='"+newPass+"' WHERE register_no='"+registerNo+"'");
					 String msg ="Hi.."+get_name+" "+registerNo+",<br>\n\n\t Your new password is "+newPass+". <br> \nLogin to MKCE-SMS portal and change password from profile.<br><br>\n\n\n With kind regards,<br>\nEliteCoders Team - MKCE.";
					 Mailer.send("from-example@gmail.com", "your-password", mailId, "Forgot Pass @ MKCE-SMS", msg);
					 return "Mail sent succesfully..";
				 }
				 else
					 return "Mail Mismatch.. ";
			 }
			
		}
		finally {
			db.closeConnection();
		}
		
		return "Uncaught Exception";
	}
	
	/**
	 * 
	 * @param registerNo
	 * @param password
	 * @return
	 * @throws SQLException
	 */
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
