package com.neotech.lesson06;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.neotech.utils.ConfigsReader;
import com.neotech.utils.Constants;

public class StoringData {

	
	//We stored the connection information in our configuration.properties file
	
	
	@Test
	public void getAndStoreData() throws SQLException
	{
		ConfigsReader.readProperties(Constants.CONFIGURATION_FILEPATH);
		Connection conn = DriverManager.getConnection(
				ConfigsReader.getProperty("dbUrl"), 
				ConfigsReader.getProperty("dbUserName"), 
				ConfigsReader.getProperty("dbPassword"));
		
		Statement st = conn.createStatement();
		
		ResultSet rs = st.executeQuery("SELECT employeeNumber, lastName, firstName, email FROM classicmodels.employees LIMIT 5;");
	
		List<Map<String, String>> listOfMaps = new ArrayList<>();
		Map<String, String> map;
		
		while(rs.next())
		{
			//we want to create a new map every time we get another row and add that to the list
			
			map = new LinkedHashMap<>();
			
			map.put("Employee Number", rs.getString("employeeNumber"));
			map.put("Last Name", rs.getString("lastName"));
			map.put("First Name", rs.getString("firstName"));
			map.put("Email", rs.getString("email"));
			
			listOfMaps.add(map);			
		}
		
		System.out.println(listOfMaps);
		
		rs.close();
		st.close();
		conn.close();
		
	}
	
	@Test
	public void getAndStoreDataEnhanced() throws SQLException
	{
		//read properties so that we can get properties
		ConfigsReader.readProperties(Constants.CONFIGURATION_FILEPATH);
		
		Connection conn = DriverManager.getConnection(
				ConfigsReader.getProperty("dbUrl"),
				ConfigsReader.getProperty("dbUserName"),
				ConfigsReader.getProperty("dbPassword"));
		
		Statement st = conn.createStatement();
		int cn = 150;
		ResultSet rs = st.executeQuery("SELECT customerNumber, contactFirstName, contactLastName, salesRepEmployeeNumber, "
				+ "creditLimit FROM customers WHERE customerNumber < " + cn);
		
		ResultSetMetaData rsMetaData = rs.getMetaData();
		int colCount = rsMetaData.getColumnCount();
		
		List<Map<String, String>> listOfMaps = new ArrayList<>();
		Map<String, String> map;
		
		while(rs.next()) //iterating through rows
		{
			//initialize a new map
			map = new LinkedHashMap<>();
			
			//fill up the map
			for (int i = 1; i <= colCount; i++)
			{
				map.put(rsMetaData.getColumnName(i), rs.getString(i));
			}
			
			//put that map in the list
			listOfMaps.add(map);
			
		}
		System.out.println(listOfMaps);
		
		
		rs.close();
		st.close();
		conn.close();
		
		
	}
}
