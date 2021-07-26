package com.sgs.student.controller;

import com.sgs.student.database.DatabaseConnector;
import com.sgs.student.database.ResultSetSerialiser;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins="http://localhost:3000/")
@RequestMapping("/api/attn")
public class AttendanceManagerController {
	
	@PostMapping("/delete")
	public String removeAtendance(@RequestParam("class_group")String classGroup,@RequestParam("attn_id")String attnId) throws SQLException
	{
		DatabaseConnector db = new DatabaseConnector();
		
		try
		{
			
			db.createConnection();
			db.query("DELETE FROM "+classGroup+"_attendance_manager WHERE attendance_id='"+attnId+"'");
			return "Attendance deleted successfully...";
		}
		catch(RuntimeException e)
		{
			return "Task Failed";
		}
		finally {
			db.closeConnection();
		}
	}
	
	@GetMapping("/get")
	public ArrayList<HashMap<String,Object>> getAttn(@RequestParam("class_group")String classGroup,@RequestParam("attn_id")String attnId) throws SQLException
	{
		DatabaseConnector db = new DatabaseConnector();
		try
		{
			db.createConnection();
			ResultSet result = db.getValue("SELECT * FROM "+classGroup+"_attendance_manager WHERE attendance_id='"+attnId+"'");
			ResultSetSerialiser rss = new ResultSetSerialiser();
			return rss.convert(result);
		}
		catch(RuntimeException e)
		{
			return null;
		}
		finally {
			db.closeConnection();
		}
	}
	
	@GetMapping("/get-all")
	public ArrayList<HashMap<String,Object>> getAllAttendance(@RequestParam("class_group")String classGroup) throws SQLException
	{	
		DatabaseConnector db = new DatabaseConnector();
		
		try
		{
			
			db.createConnection();
			ResultSet result = db.getValue("SELECT * FROM "+classGroup+"_attendance_manager");
			ResultSetSerialiser rss = new ResultSetSerialiser();
			return rss.convert(result);
		}
		catch(RuntimeException e)
		{
			return null;
		}
		finally {
			db.closeConnection();
		}
	}
	
	@PostMapping("/update-status")
	public String updateStatus(@RequestParam("class_group")String classGroup,@RequestParam("attn_id")String attnId,@RequestParam("status")String status) throws SQLException
	{
		try
		{
			DatabaseConnector db = new DatabaseConnector();
			db.createConnection();
			db.query("UPDATE "+classGroup+"_attendance_manager SET status='"+status+"' WHERE attendance_id='"+attnId+"'");
			db.closeConnection();
		}
		catch(RuntimeException e)
		{
			return "task failed";
		}
		return "Status updated Successfully...";
	}
	
	@PostMapping("/new")
	public String addNewAttendance(@RequestBody HashMap<String,String> data) throws SQLException
	{	
		try {
			String classGroup=data.get("class_group");
			String attnTitle=data.get("attendance_title");
			String attnDesc=data.get("attendance_desc");
			String attnType=data.get("attendance_type");
			String attnDate=data.get("attendance_date");
			String attnStart=data.get("attendance_strtm");
			String attnEnd=data.get("attendance_endtm");
			String attnResult=data.get("attendance_rsltlog");
			String attnStatus=data.get("attendance_status");
			String staffId=data.get("staff_id");
			
			DatabaseConnector db = new DatabaseConnector();
			db.createConnection();
			//Autogenerate Atnn Id
			ResultSet rslt = db.getValue("SELECT attendance_id FROM "+classGroup+"_attendance_manager ORDER BY attendance_id DESC limit 1");
			String newId="";
			while(rslt.next())
			{
				String tid = rslt.getString("attendance_id");
				int aid = Integer.parseInt(tid.substring(tid.length()-3));
				aid++;
				String aframe = tid.substring(0,tid.length()-3);
				newId=aframe+aid;
				
			}
			System.out.println("New Attendance Id generated - "+newId);
			//insertion
			db.query("INSERT INTO "+classGroup+"_attendance_manager VALUES ('"+newId+"','"+attnTitle+"','"+attnDesc+"','"+attnType+"','"+attnDate+"','"+attnStart+"','"+
									attnEnd+"','"+attnResult+"','"+attnStatus+"','"+staffId+"')");
			db.closeConnection();
		}
		catch(RuntimeException e){
			return "Task failed";
		}
		return "Attendance created Successfully...";
	}
	
	
	@PostMapping("/update")
	public String updateAttendance(@RequestBody HashMap<String,String> data) throws SQLException
	{	
		try {
			String classGroup=data.get("class_group");
			String attnTitle=data.get("attendance_title");
			String attnDesc=data.get("attendance_desc");
			String attnType=data.get("attendance_type");
			String attnDate=data.get("attendance_date");
			String attnStart=data.get("attendance_strtm");
			String attnEnd=data.get("attendance_endtm");
			String attnId=data.get("attendance_id");
			String attnResult=data.get("attendance_rsltlog");
			String attnStatus=data.get("attendance_status");
			String staffId=data.get("staff_id");
			
			DatabaseConnector db = new DatabaseConnector();
			db.createConnection();
			System.out.println("Updating... - "+attnId);
			//insertion
			db.query("UPDATE "+classGroup+"_attendance_manager SET attendance_title='"+attnTitle+"',attendance_desc='"+attnDesc+"',type='"+attnType+
					"',date='"+attnDate+"',start_time='"+attnStart+"',end_time='"+attnEnd+"',result_log='"+attnResult+"',status='"+attnStatus+
					"',staff_id='"+staffId+"' WHERE attendance_id='"+attnId+"'");
			db.closeConnection();
		}
		catch(RuntimeException e){
			return "Task failed";
		}
		return "Attendance updated Successfully...";
	}

}
