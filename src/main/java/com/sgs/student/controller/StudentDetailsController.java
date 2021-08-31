package com.sgs.student.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sgs.student.database.DatabaseConnector;
import com.sgs.student.database.ResultSetSerialiser;

@RestController
@CrossOrigin
@RequestMapping("/api/student-details")
public class StudentDetailsController {
	
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

}
