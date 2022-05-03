package com.sgs.student.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sgs.student.database.DatabaseConnector;
import com.sgs.student.database.ResultSetSerialiser;
/***
 * 
 * @author simclair
 * @class StudentDetailsController
 * 
 * Manages student details
 */
@RestController
@CrossOrigin
@RequestMapping("/api/student-details")
public class StudentDetailsController {
	
	/**
	 * @method getAllDetails
	 * Sends all students details
	 * Args:
	 * 		class_group - String
	 * return type -  ArrayList<HashMap<String,Object>>
	 * */
	@GetMapping("/get-all")
	public ArrayList<HashMap<String,Object>> getAllDetails(@RequestParam("class_group")String classGroup) throws SQLException
	{
		DatabaseConnector db = new DatabaseConnector(); 
		try
		{
			db.createConnection();
			ResultSet result = db.getValue("SELECT * FROM "+classGroup+"_studentdetails");
			ResultSetSerialiser rss = new ResultSetSerialiser();
			return rss.convert(result);
			
		}
		catch(RuntimeException e){return null;}
		finally{db.closeConnection();}
	}
	/**
	 * @method getDetails
	 * Sends a particular students details
	 * Args:
	 * 		class_group - String
	 * 		register_no - String
	 * return type -  ArrayList<HashMap<String,Object>>
	 * */
	@GetMapping("/get")
	public ArrayList<HashMap<String,Object>> getDetails(@RequestParam("class_group")String classGroup,@RequestParam("register_no")String registerNo) throws SQLException
	{
		DatabaseConnector db = new DatabaseConnector(); 
		try
		{
			db.createConnection();
			ResultSet result = db.getValue("SELECT * FROM "+classGroup+"_studentdetails WHERE register_no='"+registerNo+"'");
			ResultSetSerialiser rss = new ResultSetSerialiser();
			return rss.convert(result);
			
		}
		catch(RuntimeException e){return null;}
		finally{db.closeConnection();}
	}

	@GetMapping("/register")
	public String studentRegister(@RequestParam("register_no")String registerNo , @RequestParam("name")String name , @RequestParam("gender")String gender , @RequestParam("mail")String mail , @RequestParam("year")String year , @RequestParam("dept")String dept , @RequestParam("section")String section) throws SQLException{
		DatabaseConnector db = new DatabaseConnector();
		db.createConnection();
		System.out.println("step 1");
		try {
			String classGroup = year+"_"+dept+"_"+section;
			System.out.println("step 2"+classGroup);
			db.query("INSERT INTO "+classGroup+"_studentdetails VALUES('"+registerNo+"','"+name+"','"+gender+"','"+mail+"','"+classGroup.toUpperCase()+"')");
			System.out.println("step 3");
			db.query("INSERT INTO "+classGroup+"_attncount VALUES('"+registerNo+"','"+0+"')");
			System.out.println("step 4");
			db.query("INSERT INTO "+classGroup+"_attendance_log_1 VALUES('"+registerNo+"','"+name+"','"+"absent"+"')");
			System.out.println("step 5");
			db.query("INSERT INTO "+classGroup+"_attendance_log_2 VALUES('"+registerNo+"','"+name+"','"+"absent"+"')");
			Random r = new Random();
			char password = (char) (97 + r.nextInt(6));
			System.out.println("step 6");
			db.query("INSERT INTO student_login VALUES('"+registerNo+"','"+password+"','"+classGroup+"')");
			String msg ="Hi.."+name+" "+registerNo+",<br>\n\n\t Your password is "+password+". <br> \nLogin to MKCE-SMS portal and change password from profile.<br><br>\n\n\n With kind regards,<br>\nEliteCoders Team - MKCE.";
			Mailer.send("sgs.alertsys@gmail.com", "simclair@ecs", mail, "Forgot Pass @ MKCE-SMS", msg);
			return "Added Successfully";

			

		}
		catch(Exception e){
			System.out.println(e.toString());
			return "Failed";}
		
		finally {
			db.closeConnection();
			}
	
	}
}