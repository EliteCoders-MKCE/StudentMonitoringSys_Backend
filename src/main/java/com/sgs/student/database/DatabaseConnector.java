package com.sgs.student.database;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnector {
	
	Connection conn;
	
	public void createConnection() throws SQLException
	{
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_monitoring_system_db", "root", "simclair");
	}
	
	public String query(String qry) throws SQLException
	{
		Statement stmt = this.conn.createStatement();
		stmt.execute(qry);
		return "ok";
	}
	
	public ResultSet getValue(String qry) throws SQLException
	{
		Statement stmt = this.conn.createStatement();
		ResultSet result  = stmt.executeQuery(qry);
		return result;
	}
	
	public String uploadLog(String table, String name, String timestamp,String file) throws SQLException, FileNotFoundException
	{
			String query = "INSERT INTO "+table+" VALUES (?, ?, ?)";
	      PreparedStatement pstmt = conn.prepareStatement(query);
	      pstmt.setString(1, name);
	      pstmt.setString(2, timestamp);
	      FileInputStream fin = new FileInputStream(file);
	      pstmt.setBinaryStream(3, fin);
	      pstmt.execute();
	      return "ok";
	}
	
	public void closeConnection() throws SQLException
	{
		this.conn.close();
	}
}
