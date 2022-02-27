package com.sgs.student.controller;

import com.opencsv.CSVWriter;
import com.sgs.student.database.DatabaseConnector;
import com.sgs.student.database.ResultSetSerialiser;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.core.io.Resource;
/**
 * 
 * @author simclair
 * @class AttendanceManagerController
 *  Main API controller for Attendance Management
 */
@RestController
@CrossOrigin
@RequestMapping("/api/attn")
public class AttendanceManagerController {

	
	/*
	 * Continuous Attendance - Counter and status manager
	 * Args: 
	 * 	class_group -> string
	 * 	atten_id    -> string
	 * 	register_no -> string
	 * 
	 * 	Return Type -> string
	 * */
	@GetMapping("/count-cont")
	public String countContinuous(@RequestParam("class_group")String classGroup,@RequestParam("attn_id")String attnId,@RequestParam("register_no")String registerNo) throws SQLException
	{
		try
		{
			DatabaseConnector db = new DatabaseConnector();
			db.createConnection();
			ResultSetSerialiser rss = new ResultSetSerialiser();
			ArrayList<HashMap<String,Object>> arr= rss.convert(db.getValue("SELECT * FROM "+classGroup+"_attendance_manager WHERE attendance_id='"+attnId+"'"));
			for(HashMap<String,Object> hashmap:arr)
			{
				int hrs  = LocalDateTime.now().getHour();
				int min = LocalDateTime.now().getMinute();
				int sec = LocalDateTime.now().getSecond();
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		        Date date = new Date();
				if(hashmap.get("date").equals(dateFormat.format(date)))
				{
					LocalTime target = LocalTime.parse((String.format("%02d:%02d:%02d",hrs,min,sec))) ;
					Boolean targetInZone = ( 
					    target.isAfter( LocalTime.parse((String) hashmap.get("start_time")) ) 
					    && 
					    target.isBefore( LocalTime.parse((String) hashmap.get("end_time")) ) 
					) ;
					db.query("UPDATE "+classGroup+"_attncount SET count=count+1 WHERE register_no='"+registerNo+"'");
					if(targetInZone)
							return "continue";
					else
						return "stop";
					
				}
			}
			return "Nothing found";
		}
		catch(RuntimeException e)
		{
			return "Unexpected error";
		}
	}
	
	
	/*
	 *  Live attendance or Current State of attendance  will be returned
	 * 	Args:
	 * 		result_log -> string 
	 * 	Return Type -> ArrayList[map<string>,<string>]
	 * 
	 * */
	@GetMapping("/get-liveatn")
	public ArrayList<HashMap<String,Object>> getAttendance(@RequestParam("result_log")String resultLog) throws SQLException
	{
		DatabaseConnector db = new DatabaseConnector();
		try
		{
			db.createConnection();
			ResultSet result = db.getValue("SELECT * FROM "+resultLog);
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
	
	
	/*
	 * Delete the file log from db with file_id[date_time] given
	 * Args:
	 * 	class_group -> string
	 * 	file_id -> string
	 * Return Type -> string
	 * */
	@GetMapping("/delete-log")
	public String deleteLog(@RequestParam("class_group")String classGroup,@RequestParam("file_id")String fileId) throws SQLException
	{
		DatabaseConnector db = new DatabaseConnector();
		try
		{
			db.createConnection();
			db.query("DELETE FROM "+classGroup+"_attendance_file_log WHERE date_time='"+fileId+"'");
			return "Log deleted...";
		}
		catch(RuntimeException e)
		{
			return "Error occured..";
		}
		finally {
			db.closeConnection();
		}
	}
	
	
	/*
	 * Downloads the reqd file with given file id
	 * Args:
	 * 	class_group -> string
	 * 	file_id -> string
	 * Return type -> Bin data as ResponseEntity
	 * */
	@GetMapping("/download")
	public ResponseEntity<Resource> downloadAttendanceFile(@RequestParam("class_group")String classGroup,@RequestParam("file_id")String fileId) throws SQLException, IOException
	{
		DatabaseConnector db = new DatabaseConnector();
		
		try
		{
			
			db.createConnection();
			ResultSet rs = db.getValue("SELECT file FROM "+classGroup+"_attendance_file_log WHERE date_time='"+fileId+"'");
			while(rs.next())
			{
				InputStream inputStream = rs.getBinaryStream("file");
		         byte byteArray[] = new byte[inputStream.available()];
		         inputStream.read(byteArray);
		         FileOutputStream outPutStream = new FileOutputStream("downloaded.csv");
		         outPutStream.write(byteArray);
		         outPutStream.close();
		         System.out.println("file saved");
		         File file = new File("downloaded.csv");

		         HttpHeaders header = new HttpHeaders();
		         header.add(HttpHeaders.CONTENT_DISPOSITION,
		        		 "attachment; filename="+(classGroup.toUpperCase()).replaceAll("_","-")+" "+fileId+".csv");
		         header.add("Cache-Control", "no-cache, no-store, must-revalidate");
		         header.add("Pragma", "no-cache");
		         header.add("Expires", "0");

		         Path path = Paths.get(file.getAbsolutePath());
		         ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));

		         return ResponseEntity.ok()
		                 .headers(header)
		                 .contentLength(file.length())
		                 .contentType(MediaType.parseMediaType("application/octet-stream"))
		                 .body(resource);	         
			}
			
		}
		catch(RuntimeException e)
		{
			return null;
		}
		finally {
			db.closeConnection();
		}
		return null;
	}
	
	
	/*
	 * Deletes an attendance from attendance manager
	 * Args: 
	 * 	class_group -> string
	 * 	atten_id    -> string
	 * 
	 * Return Type -> string
	 * */
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
	
	
	/*
	 * Retrieves a single attendance from attendance manager
	 * Args: 
	 * 	class_group -> string
	 * 	atten_id    -> string
	 * 
	 * Return Type -> ArrayList[Hashmap<string,object>]
	 * */
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
	
	
	/*
	 * Retrieves all attendances from attendance manager
	 * Args: 
	 * 	class_group -> string
	 * 
	 * Return Type -> ArrayList[Hashmap<string,object>]
	 * */
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
	
	
	/*
	 * Updates status in manager, resets db values if reqd  if its continuous 
	 * Args: 
	 * 	class_group -> string
	 * 	atten_id    -> string
	 * 	status  -> string
	 * Return Type -> string
	 * */
	@PostMapping("/update-status")
	public String updateStatus(@RequestParam("class_group")String classGroup,@RequestParam("attn_id")String attnId,@RequestParam("status")String status) throws SQLException, ParseException
	{
		try
		{
			DatabaseConnector db = new DatabaseConnector();
			db.createConnection();
				ResultSetSerialiser rss = new ResultSetSerialiser();
				ArrayList<HashMap<String,Object>> arr= rss.convert(db.getValue("SELECT * FROM "+classGroup+"_attendance_manager WHERE attendance_id='"+attnId+"'"));
				for(HashMap<String,Object> hashmap:arr)
				{
					if(hashmap.get("type").equals("cont"))
					{
						if(status.equals("false"))
						{
							int minval=0;
							ResultSet rslt = db.getValue("SELECT count FROM "+classGroup+"_attncount WHERE register_no='minval'");
							while(rslt.next())
							{
								minval = rslt.getInt("count"); 
							}
							ArrayList<HashMap<String,Object>> arr1= rss.convert(db.getValue("SELECT register_no FROM "+classGroup+"_attncount WHERE count >= "+minval+""));
							for(HashMap<String,Object> hash:arr1)
								db.query("UPDATE "+hashmap.get("result_log")+" SET status='present' WHERE register_no='"+hash.get("register_no")+"'");
							db.query("UPDATE "+classGroup+"_attendance_manager SET status='"+status+"' WHERE attendance_id='"+attnId+"'");
							return "cont-attendance ready to generate report..";
						}
						else if(status.equals("true"))
						{
							SimpleDateFormat format = new SimpleDateFormat("HH:mm");
							Date start = format.parse((String)hashmap.get("start_time"));
							Date end = format.parse((String)hashmap.get("end_time"));
							long difference = end.getTime()-start.getTime();
							long mins = difference/60000;
							mins /= 2;
							int padding = (int)(mins*(25.0f/100.0f));
							int minval = (int)mins-padding;
							db.query("UPDATE "+classGroup+"_attncount SET count=0 WHERE 1");
							db.query("UPDATE "+hashmap.get("result_log")+" SET status='absent' WHERE 1");
							db.query("UPDATE "+classGroup+"_attncount SET count="+minval+" WHERE register_no='minval'");
							db.query("UPDATE "+classGroup+"_attendance_manager SET status='"+status+"' WHERE attendance_id='"+attnId+"'");
						}
					}
					else
						db.query("UPDATE "+classGroup+"_attendance_manager SET status='"+status+"' WHERE attendance_id='"+attnId+"'");
				}
			
			db.closeConnection();
		}
		catch(RuntimeException e)
		{
			return "task failed";
		}
		return "Status updated Successfully...";
	}
	
	
	/*
	 * Resets a log and saves it as blob
	 * Args: 
	 * 	class_group -> string
	 * 	atten_id    -> string
	 * Return Type -> string
	 * */
	@SuppressWarnings("deprecation")
	@PostMapping("/reset-save")
	public String resetAndSave(@RequestParam("class_group")String classGroup,@RequestParam("attn_id")String attnId) throws SQLException, IOException
	{
		try
		{
			DatabaseConnector db = new DatabaseConnector();
			db.createConnection();
			ResultSetSerialiser rss = new ResultSetSerialiser();
			ArrayList<HashMap<String,Object>> arr= rss.convert(db.getValue("SELECT * FROM "+classGroup+"_attendance_manager WHERE attendance_id='"+attnId+"'"));
			for(HashMap<String,Object> hashmap:arr)
			{
				CSVWriter writer = new CSVWriter(new FileWriter("temp.csv"), ',');
				ResultSet filedata = db.getValue("SELECT * FROM "+hashmap.get("result_log"));
				writer.writeAll(filedata,true);
				writer.close();
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
				String timestamp  = dateFormat.format(new Date());
				db.uploadLog(classGroup+"_attendance_file_log",attnId+" "+hashmap.get("attendance_title")+" "+hashmap.get("date"),timestamp,"temp.csv");
				db.query("UPDATE "+hashmap.get("result_log")+" SET status='absent'");
				return "Reset and Save Successfull...";
			}
		}
		catch(RuntimeException e)
		{
			return "Task failed";
		}
		return "Reset/Save on hold..";
	}
	
	
	/*
	 * Creates new Attendance in manager
	 * Args:
	 * 	 Hashmap<string,string>
	 * Return Type -> string
	 * */
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
	
	
	/*
	 * Edit/Updates attendance in manager
	 * Args:
	 * 	 Hashmap<string,string>
	 * Return Type -> string
	 * */
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
	
	
	/*
	 * Sends enabled attendance to students portal
	 *  Args: 
	 * 	class_group -> string
	 * 	register_no -> string
	 * 
	 * Return Type -> ArrayList[Hashmap<string,object>]
	 * */
	@GetMapping("/get-enabled")
	public ArrayList<HashMap<String,Object>> getEnabledAttendance(@RequestParam("class_group")String classGroup,@RequestParam("register_no")String registerNo) throws SQLException
	{
		try
		{
			DatabaseConnector db = new DatabaseConnector();
			db.createConnection();
			ResultSetSerialiser rss = new ResultSetSerialiser();
			ArrayList<HashMap<String,Object>> arr= rss.convert(db.getValue("SELECT * FROM "+classGroup+"_attendance_manager WHERE status='true'"));
			ArrayList<HashMap<String,Object>> response = new ArrayList<HashMap<String,Object>>();
			for(HashMap<String,Object> hashmap:arr)
			{
				int hrs  = LocalDateTime.now().getHour();
				int min = LocalDateTime.now().getMinute();
				int sec = LocalDateTime.now().getSecond();
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		        Date date = new Date();
				if(hashmap.get("date").equals(dateFormat.format(date)))
				{
					LocalTime target = LocalTime.parse((String.format("%02d:%02d:%02d",hrs,min,sec))) ;
					Boolean targetInZone = ( 
					    target.isAfter( LocalTime.parse((String) hashmap.get("start_time")) ) 
					    && 
					    target.isBefore( LocalTime.parse((String) hashmap.get("end_time")) ) 
					) ;
					if(targetInZone)
					{
							ResultSet rslt = db.getValue("SELECT status FROM "+hashmap.get("result_log")+" WHERE register_no='"+registerNo+"'");
							while(rslt.next())
								hashmap.put("atn_status",rslt.getString("status"));
							response.add(hashmap);
					}
					
				}
			}
			return response;
		}
		catch(RuntimeException e)
		{
			return null;
		}
	}
	
	
	/*
	 * Gives attendance to students in single attendance mode
	 * Args: 
	 * 	class_group -> string
	 * 	register_no -> string
	 * 
	 * Return Type -> string
	 * */
	@GetMapping("/give-attendance")
	public String giveAttendance(@RequestParam("class_group")String classGroup,@RequestParam("register_no")String registerNo,@RequestParam("attendance_id")String attnId) throws SQLException
	{
		try
		{
			DatabaseConnector db = new DatabaseConnector();
			db.createConnection();
			ResultSetSerialiser rss = new ResultSetSerialiser();
			ArrayList<HashMap<String,Object>> arr= rss.convert(db.getValue("SELECT * FROM "+classGroup+"_attendance_manager WHERE attendance_id='"+attnId+"'"));
			for(HashMap<String,Object> hashmap:arr)
			{
				db.query("UPDATE "+hashmap.get("result_log")+" SET status='present' WHERE register_no='"+registerNo+"'");
				return "Attendance Given Successfully..";
			}
		}
		catch(RuntimeException e)
		{
			return "Exception occured";
		}
		
		return "Something Failed...";
	}
	
	
	/*
	 * Returns all available log files
	 * Args: 
	 * 	class_group -> string
	 * 
	 * Return Type -> ArrayList[Hashmap<string,object>]
	 * */
	@GetMapping("/get-log")
	public ArrayList<HashMap<String,Object>> getAvailableLogs(@RequestParam("class_group")String classGroup) throws SQLException
	{
		try
		{
			DatabaseConnector db = new DatabaseConnector();
			db.createConnection();
			ResultSetSerialiser rss = new ResultSetSerialiser();
			ArrayList<HashMap<String,Object>> arr= rss.convert(db.getValue("SELECT attendance_id,date_time FROM "+classGroup+"_attendance_file_log"));
			Collections.reverse(arr);
			return arr;
		}
		catch(RuntimeException e)
		{
			return null;
		}
	}
	
}
