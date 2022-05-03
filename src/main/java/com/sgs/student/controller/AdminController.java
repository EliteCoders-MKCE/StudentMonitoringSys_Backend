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
/***
 * 
 * @author Sasikumar
 * @Class AdminController
 *
 */
@RestController
@CrossOrigin

@RequestMapping("api/admin")
public class AdminController {
	/**
	 * 
	 * @param className
	 * @return String
	 * @throws SQLException
	 */

	@GetMapping("/create-class")
	public String createClass(@RequestParam("class_name")String className)throws SQLException
	{
		
		DatabaseConnector db = new DatabaseConnector();
		db.createConnection();
		
		try {
			
			db.query("CREATE TABLE "+className+"_studentdetails (register_no VARCHAR(10),name VARCHAR(30),gender VARCHAR(20),e_mail VARCHAR(30),dept_class VARCHAR(25) ,PRIMARY KEY (register_no))");
			db.query("CREATE TABLE "+className+"_attendance_manager (attendance_id VARCHAR(50),attendance_title VARCHAR(50),attendance_desc VARCHAR(100),type VARCHAR(20), date VARCHAR(15) , start_time VARCHAR(25) , end_time VARCHAR(25) , result_log VARCHAR(35),status VARCHAR(25) , staff_id VARCHAR(25),PRIMARY KEY (attendance_id) )");
			db.query("CREATE TABLE "+className+"_attncount (register_no VARCHAR(10),count INTEGER ,PRIMARY KEY (register_no))");
			db.query("INSERT INTO "+className+"_attncount VALUES('"+"minval"+"','"+100+"')");
			db.query("CREATE TABLE "+className+"_attendance_log_1 (register_no VARCHAR(10),name VARCHAR(30),status VARCHAR(20),PRIMARY KEY (register_no))");
			db.query("CREATE TABLE "+className+"_attendance_log_2 (register_no VARCHAR(10),name VARCHAR(30),status VARCHAR(20),PRIMARY KEY (register_no))");
			db.query("CREATE TABLE "+className+"_chat (message TEXT,sender VARCHAR(50),date_time VARCHAR(50),priority VARCHAR(10) )");
			db.query("CREATE TABLE "+className+"_attendance_file_log (attendance_id VARCHAR(100),date_time VARCHAR(40),file MEDIUMBLOB ,PRIMARY KEY (attendance_id))");
			return "Created Successfully";
			

		}
		catch(Exception e){return "Failed";}
		
		finally {
			db.closeConnection();}
		
		
	
	}
	
	/**
	 * 
	 * @param className
	 * @return String
	 * @throws SQLException
	 */
	@GetMapping("/delete-class")
	public String deleteClass(@RequestParam("class_name")String className)throws SQLException{
		
		DatabaseConnector db = new DatabaseConnector();
		db.createConnection();
		
		try {
			
			db.query("DROP TABLE "+className+"_studentdetails ,"+className+"_attendance_manager,"+className+"_attncount,"+className+"_attendance_log_1,"+className+"_attendance_log_2,"+className+"_chat,"+className+"_attendance_file_log");
			
			return "Deleted Successfully";


		}
		catch(Exception e){return "Failed";}
		
		finally {
			db.closeConnection();
			}
	}
	
		/**
		 * 
		 * @param staffId
		 * @param name
		 * @param mail
		 * @param deptClassGroup
		 * @return String
		 * @throws SQLException
		 */
	
		@GetMapping("/add-staff")
		public String addStaff(@RequestParam("staff_id")String staffId,@RequestParam("name")String name ,@RequestParam("mail")String mail ,@RequestParam("dept_class_group")String deptClassGroup)throws SQLException{
			
			DatabaseConnector db = new DatabaseConnector();
			db.createConnection();
			try {
				
				db.query("INSERT INTO staff_details VALUES('"+staffId.toUpperCase()+"','"+name+"','"+mail+"','"+deptClassGroup.toUpperCase().replaceAll("_", "-")+"')");
				db.query("INSERT INTO staff_login VALUES('"+staffId.toUpperCase()+"','"+staffId+"','"+deptClassGroup.toUpperCase().replaceAll("_", "-")+"')");
				
				return "Added Successfully";

				

			}
			catch(Exception e){return "Failed";}
			
			finally {
				db.closeConnection();
				}
		
		
		
	}
		
		/**
		 * 
		 * @param staffId
		 * @return String
		 * @throws SQLException
		 */
		@GetMapping("/delete-staff")
		public String deleteStaff(@RequestParam("staff_id")String staffId)throws SQLException{
			
			DatabaseConnector db = new DatabaseConnector();
			db.createConnection();
			try {
				
				db.query("DELETE FROM staff_details WHERE staff_id='"+staffId+"'");
				db.query("DELETE FROM staff_login WHERE staff_id='"+staffId+"'");
				
				return "Deleted Successfully";

				

			}
			catch(Exception e){return "Failed";}
			
			finally {
				db.closeConnection();
				}
		}
			
		/**
		 * 
		 * @param registerNo
		 * @param deptClass
		 * @return String
		 * @throws SQLException
		 */
		
	@GetMapping("/remove-student")
		public String removeStudent(@RequestParam("register_no")String registerNo,@RequestParam("dept_class")String deptClass)throws SQLException{
			
			DatabaseConnector db = new DatabaseConnector();
			db.createConnection();
			try {
				System.out.print("yes inside");
				db.query("DELETE FROM "+deptClass+"_studentdetails WHERE register_no ='"+registerNo+"'");
				db.query("DELETE FROM "+deptClass+"_attncount WHERE register_no ='"+registerNo+"'");
				db.query("DELETE FROM "+deptClass+"_attendance_log_1 WHERE register_no ='"+registerNo+"'");
				db.query("DELETE FROM "+deptClass+"_attendance_log_2 WHERE register_no ='"+registerNo+"'");
				db.query("DELETE FROM student_login WHERE register_no ='"+registerNo+"'");
				return "Deleted Successfully";

				

			}
			catch(Exception e){return "Failed";}
			
			finally {
				db.closeConnection();
				}
	
		
	}
	
	/**
	 * 
	 * @return ArrayList<HashMap<String,Object>>
	 * @throws SQLException
	 */
	@GetMapping("get-allstaff")
	public ArrayList<HashMap<String,Object>> getAllStaffDetails() throws SQLException
	{
		DatabaseConnector db = new DatabaseConnector(); 
		try
		{
			db.createConnection();
			ResultSet result = db.getValue("SELECT * FROM staff_details");
			ResultSetSerialiser rss = new ResultSetSerialiser();
			return rss.convert(result);
			
		}
		catch(RuntimeException e){return null;}
		finally{db.closeConnection();}
	}
	
	/**
	 * 
	 * @param staffId
	 * @return ArrayList<HashMap<String,Object>>
	 * @throws SQLException
	 */
	
	@GetMapping("/get-staff")
	public ArrayList<HashMap<String,Object>> getstaffDetails(@RequestParam("staff_id")String staffId) throws SQLException
	{
		DatabaseConnector db = new DatabaseConnector(); 
		try
		{
			db.createConnection();
			ResultSet result = db.getValue("SELECT * FROM staff_details WHERE staff_id = '"+staffId.toUpperCase()+"'");
			ResultSetSerialiser rss = new ResultSetSerialiser();
			return rss.convert(result);
			
		}
		catch(RuntimeException e){return null;}
		finally{db.closeConnection();}
	}

}