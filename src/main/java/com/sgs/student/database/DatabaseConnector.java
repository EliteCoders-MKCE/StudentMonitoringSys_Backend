package com.sgs.student.database;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 
 * @author simclair
 * @class DatabaseConnector
 *  Simplified Database connector
 */
public class DatabaseConnector {
	
	Connection conn;
	/**
	 * @method createConnection
	 * @throws SQLException
	 */
	public void createConnection() throws SQLException
	{
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_monitoring_system_db", "root", "sasisk");
	}
	/**
	 * @method query
	 * @param qry
	 * @return
	 * @throws SQLException
	 */
	public String query(String qry) throws SQLException
	{
		Statement stmt = this.conn.createStatement();
		stmt.execute(qry);
		return "ok";
	}
	/**
	 * @method getValue
	 * @param qry
	 * @return
	 * @throws SQLException
	 */
	public ResultSet getValue(String qry) throws SQLException
	{
		Statement stmt = this.conn.createStatement();
		ResultSet result  = stmt.executeQuery(qry);
		return result;
	}
	/**
	 * @method uploadLog
	 * @param table
	 * @param name
	 * @param timestamp
	 * @param file
	 * @return
	 * @throws SQLException
	 * @throws FileNotFoundException
	 */
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
	
	/**
	 * @method closeConnection
	 * @throws SQLException
	 */
	public void closeConnection() throws SQLException
	{
		this.conn.close();
	}
}
