package com.sgs.student.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sgs.student.database.DatabaseConnector;
import com.sgs.student.database.ResultSetSerialiser;

@RestController
@CrossOrigin(origins="http://localhost:3000/")
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
	
	@GetMapping("/profile")
	public ArrayList<HashMap<String,Object>>getDetails(@RequestParam("class_group")String classGroup , @RequestParam("register_no")String registerNo)throws SQLException
	{
		
		DatabaseConnector db = new DatabaseConnector();
		try {
			db.createConnection();
			ResultSet result = db.getValue("SELECT * FROM "+classGroup+"_studentdetails WHERE register_no='"+registerNo+"'");
			ResultSetSerialiser rss = new ResultSetSerialiser();
			return rss.convert(result);
		}
		catch (RuntimeException e) {return null;}
		finally {db.closeConnection();}
	}
	
@GetMapping("/status")
public String statusCheck(@RequestParam("class_group")String classGroup,@RequestParam("register_no")String registerNo) throws SQLException
{
	DatabaseConnector db = new DatabaseConnector();
	try {
		db.createConnection();
		String check="true";
		 ResultSet result = db.getValue("SELECT * FROM "+classGroup+"_attendance_manager WHERE status='"+check+"'");
		
		//System.out.println(result.getObject("result_log"));
		return "ok";
				
		}catch (RuntimeException e) {return null;}
	finally {db.closeConnection();}
	
}
/*
@PostMapping("/attendance")
public String takeAttendance(@RequestBody ArrayList<HashMap<String, Object>> arrayList) throws SQLException
{
	DatabaseConnector db = new DatabaseConnector();
	try {
		db.createConnection();
		ResultSet result =db.getValue("SELECT status FROM"+resultLog+"WHERE register_no='"+registerNo+"'");
		
	}catch (RuntimeException e) {return null;}
	finally {db.closeConnection();}
	System.out.println(arrayList);
	/*String classGroup=arrayList
	String startTime=arrayList.get("start_time");
	String endTime=arrayList.get("end_time");
	String resultLog=arrayList.get("result_log");
	String attnStatus=arrayList.get("attendance_status");
	
	String[] start_time =startTime.split(":");
	String start_time_hour =start_time[0];
	String start_time_minute = start_time[1];
	int start_hour =Integer.parseInt(start_time_hour);
	int start_minute =Integer.parseInt(start_time_minute);
	
	LocalTime local = LocalTime.now();
	String localTime = String.valueOf(local);
	String[] end_time = endTime.split(":");
	String end_time_hour = end_time[0];
	String end_time_minute = end_time[1];
	
	
	int end_hour=Integer.parseInt(end_time_hour); 
	int end_minute=Integer.parseInt(end_time_minute);
	String[] current = localTime.split(":");
	String currenthour = current[0];
	String currentminute = current[1];
	int currentHour=Integer.parseInt(currenthour);
	int currentMinute=Integer.parseInt(currentminute);
	
	System.out.println(end_hour);
	System.out.println(end_minute);
	System.out.println(currenthour);
	System.out.println(currentMinute);
	
	if(start_hour<=currentHour && start_minute <=currentMinute && end_hour >= currentHour && end_minute >= currentMinute)
	{System.out.println("Yes ulla varuthu");
	
	try {
		String status="true";
		db.createConnection();
		db.query("UPDATE "+resultLog+" SET status='"+status+"' WHERE register_no='"+registerNo+"'");
        db.closeConnection();	
	}
	catch(RuntimeException e)
	{
		return "task failed";
	}}
	return "Status updated Successfully...";
	

}*/



}