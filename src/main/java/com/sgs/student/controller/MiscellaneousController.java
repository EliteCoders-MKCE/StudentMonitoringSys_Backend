package com.sgs.student.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sgs.student.database.DatabaseConnector;
import com.sgs.student.database.ResultSetSerialiser;

/***
 * 
 * @author simclair
 * @class MiscellaneousController 
 * 
 *	Misc controller for Chat management
 */
@RestController
@CrossOrigin
@RequestMapping("/api/misc")
public class MiscellaneousController {
	
	/**
	 * 
	 * @param classGroup
	 * @return ArrayList<HashMap<String,Object>>
	 * @throws SQLException
	 */
	@GetMapping("/get-chat")
	public ArrayList<HashMap<String,Object>> getChat(@RequestParam("class_group")String classGroup) throws SQLException
	{
		DatabaseConnector db = new DatabaseConnector();
		try
		{
			db.createConnection();
			ResultSet result = db.getValue("SELECT * FROM "+classGroup+"_chat");
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
	
	/**
	 * 
	 * @param classGroup
	 * @param message
	 * @param sender
	 * @param priority
	 * @return String
	 * @throws SQLException
	 */
	@PostMapping("/save-message")
	public String saveNewMessage(@RequestParam("class_group")String classGroup,@RequestParam("message")String message,@RequestParam("sender")String sender,@RequestParam("priority")String priority) throws SQLException
	{
		DatabaseConnector db = new DatabaseConnector();
		try
		{
			db.createConnection();
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			String timestamp  = dateFormat.format(new Date());
			db.query("INSERT INTO "+classGroup+"_chat VALUES ('"+message.replaceAll("/n","\n")+"','"+sender+"','"+timestamp+"','"+priority+"')");
			return "Message added to chat..";
		}
		catch(RuntimeException e)
		{
			return "Exception occured..";
		}
		finally {
			db.closeConnection();
		}
	}
	
	/**
	 * 
	 * @param classGroup
	 * @param messageId
	 * @return String
	 * @throws SQLException
	 */
	@GetMapping("/delete-msg")
	public String deleteMessage(@RequestParam("class_group")String classGroup,@RequestParam("message_id")String messageId) throws SQLException
	{
		DatabaseConnector db = new DatabaseConnector();
		try
		{
			db.createConnection();
			db.query("DELETE FROM "+classGroup+"_chat WHERE date_time='"+messageId+"'");
			return "Message Deleted..";
		}
		catch(RuntimeException e)
		{
			return "Exception occured..";
		}
		finally {
			db.closeConnection();
		}
	}
}
