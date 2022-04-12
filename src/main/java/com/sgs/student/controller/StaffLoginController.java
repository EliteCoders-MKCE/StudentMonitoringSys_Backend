package com.sgs.student.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sgs.student.database.DatabaseConnector;
import com.sgs.student.database.ResultSetSerialiser;


@RestController
@CrossOrigin
@RequestMapping("api/loginstaff")
public class StaffLoginController {

	@GetMapping("/get-name")
	public String staffName(@RequestParam("staff_id")String staffId)
	{
		try
		{
			DatabaseConnector db = new DatabaseConnector();
			db.createConnection();
			ResultSetSerialiser rss = new ResultSetSerialiser();
			ArrayList<HashMap<String,Object>> arr = rss.convert(db.getValue("SELECT * FROM staff_details WHERE staff_id='"+staffId+"'"));
			for(HashMap<String,Object> hash:arr)
			{
				return (String) hash.get("name");
			}
		}
		catch(Exception ex)
		{
			return "Faculty Portal";
		}
		return "Faculty Portal";
	}
	
	
	@PostMapping("/login")
	public  HashMap<String,String> login(@RequestParam("staff_id")String staffId,@RequestParam("password")String password)throws SQLException
	{
		DatabaseConnector db = new DatabaseConnector();
		db.createConnection();
		try 
		{
			 ResultSet result =db.getValue("SELECT * FROM staff_login WHERE staff_id='"+staffId+"'");
			 while(result.next()) 
			 {
				 String get_password = result.getString("password");
				 String classgroup = result.getString("class_group");
				 
				 
				 if(password.equals(get_password))
				 {
					 HashMap<String,String> hash = new HashMap<String,String>();
					 hash.put("staffId",staffId);
					 hash.put("classGroup", classgroup);
					 hash.put("loginStatus","true");
					 return hash;
				 } 
			 }
			 		
			
		}catch(RuntimeException e) {
			return null;}
		finally {
			db.closeConnection();
		}
		 HashMap<String,String> response = new HashMap<String,String>();
         response.put("loginStatus","false");
         return response;
			
		
		
	}
	
}