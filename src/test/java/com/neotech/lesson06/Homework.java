package com.neotech.lesson06;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class Homework {

	
	/*
	 * Connect to classicmodels database 
	 * Execute a query to get all information of customer with id 124 
	 * Get the resultset metadata 
	 * Print the number of columns
	 * Get all the column names and store them in an arraylist 
	 * Print the Arraylist
	 */
	
	public static String dbUserName = "user1";
	public static String dbPassword = "Neotech@123";

	// DB url format: jdbc:jdbctype://ipaddress:portnumber/db_name
	public static String dbUrl = "jdbc:mysql://147.182.216.34:3306/classicmodels";
	
	
	@Test
	public void getResults() throws SQLException
	{
		
		// create a connection to the database
		Connection conn = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
		
		//create an envelope to sent a letter to the database (to ask a question)
		Statement st = conn.createStatement();
		
		ResultSet rs = st.executeQuery("SELECT CONCAT(contactFirstName, ' ', contactLastName) AS FullName, phone, state FROM customers WHERE customerNumber < 124");

		
		//get information about the structure of the resultSet 
		ResultSetMetaData rsMetaData = rs.getMetaData();
		
		
		//get number of columns
		int colCount = rsMetaData.getColumnCount();
		System.out.println("There are " + colCount + " columns");
		
		String firstColumnName = rsMetaData.getColumnName(1);
		System.out.println("The first column name is: " + firstColumnName);
		
		
		//get all column names
		
		List<String> list = new ArrayList<>();
		for (int i = 1; i <= colCount; i++)
		{
			list.add(rsMetaData.getColumnName(i));
			
			//in two lines
//			String colName = rsMetaData.getColumnName(i);
//			list.add(colName);
			
		}
		System.out.println(list);
		
		
		//how do we read data??
	//	rs.next(); //this is moving the cursor to the first row
	//	String fullName = rs.getString("FullName");
	//	String phone = rs.getString("phone");
	//	System.out.println(fullName + ' ' + phone);
		
		while(rs.next())
		{
			String fullName = rs.getString("FullName");
			String phone = rs.getString("phone");
			System.out.println(fullName + ' ' + phone);
		}
		
		rs.close();
		st.close();
		conn.close();
		
	}
	
}
