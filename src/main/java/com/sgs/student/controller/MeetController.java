package com.sgs.student.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLException;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sgs.student.database.DatabaseConnector;
import com.sgs.student.database.ResultSetSerialiser;

/**
 * 
 * @author simclair
 * @class MeetController
 * 		Used for creating and managing meets
 *
 */
@RestController
@CrossOrigin
@RequestMapping("api/meet")
public class MeetController {
	
	/**
	 * @method getAvailableMeets
	 * @param classGroup
	 * @return
	 * @throws SQLException
	 */
	@GetMapping("/get-meets")
	public ArrayList<HashMap<String,Object>> getAvailableMeets(@RequestParam("class_group") String classGroup) throws SQLException{
		try
		{
			DatabaseConnector db = new DatabaseConnector();
			db.createConnection();
			ResultSetSerialiser rss = new ResultSetSerialiser();
			ArrayList<HashMap<String,Object>> result= rss.convert(db.getValue("SELECT * FROM "+classGroup+"_meets"));
			return result;
		}
		catch(RuntimeException e)
		{
			return null;
			
		}
	}
	
	/**
	 * @method createNewMeet
	 * @param classGroup
	 * @param title
	 * @param desc
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 */
	@GetMapping("/create")
	public String createNewMeet(@RequestParam("class_group") String classGroup,@RequestParam("title") String title,@RequestParam("desc")String desc) throws IOException, SQLException {
		try {
			String urlString = "https://sms-meet-backend.azurewebsites.net/create-group";
			URL url = new URL(urlString);
			URLConnection conn = url.openConnection();
			InputStream is = conn.getInputStream();
			Scanner s = new Scanner(is).useDelimiter("\\A");
			 String[] result = (s.hasNext() ? s.next() : "").split("Created Group id : ");
			 String generatedMeetId = result[1];
			 //System.out.println(generatedMeetId);
			 DatabaseConnector db = new DatabaseConnector();
			 db.createConnection();
			 db.query("INSERT INTO "+classGroup+"_meets VALUES ('"+generatedMeetId+"','"+title+"','"+desc+"')");
			 db.closeConnection();
			 return "Meet created successfully...";
			 
		}
		catch(Exception e) {
			System.out.println("Execption @ meet controller");
			return "Meet creation failed";
		}
	}
	
}
