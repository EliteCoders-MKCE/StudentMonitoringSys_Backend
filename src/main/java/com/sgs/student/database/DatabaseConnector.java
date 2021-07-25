package com.sgs.student.database;

import java.sql.Connection;
import java.sql.DriverManager;
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
	
	public void closeConnection() throws SQLException
	{
		this.conn.close();
	}
}
