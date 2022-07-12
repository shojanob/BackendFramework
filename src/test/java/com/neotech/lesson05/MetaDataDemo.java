package com.neotech.lesson05;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

public class MetaDataDemo {

	public static String dbUserName = "user1";
	public static String dbPassword = "Neotech@123";

	// DB url format: jdbc:jdbctype://ipaddress:portnumber/db_name
	public static String dbUrl = "jdbc:mysql://147.182.216.34:3306/classicmodels";

	@Test
	public void dbMetaData() throws SQLException {
		Connection conn = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
		DatabaseMetaData dbMetaData = conn.getMetaData();

		String driverName = dbMetaData.getDriverName();
		System.out.println(driverName);

		String dbVersion = dbMetaData.getDatabaseProductVersion();
		System.out.println(dbVersion);

		String dbName = dbMetaData.getDatabaseProductName();
		System.out.println(dbName);

	}

	// Display all info regarding the employee with employeeNumber = 1076
	@Test
	public void rsMetaData() throws SQLException {
		Connection conn = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);

		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM employees WHERE employeeNumber = 1076");

		// Get the information about ResultSet
		ResultSetMetaData rsMetaData = rs.getMetaData();

		// Get the total number of the columns
		int columnCount = rsMetaData.getColumnCount();
		System.out.println("columnCount -> " + columnCount);

		// Get the name of the first column
		String columnName1 = rsMetaData.getColumnName(1);
		System.out.println("columnName1 -> " + columnName1);

		// Get the name of the 3rd column
		String columnName3 = rsMetaData.getColumnName(3);
		System.out.println("columnName3 -> " + columnName3);

		System.out.println("--- Print all column names on the result set ---");
		for (int i = 1; i <= columnCount; i++) {
			String columnName = rsMetaData.getColumnName(i);
			System.out.println("columnName" + i + " -> " + columnName);
		}

		System.out.println("--- Print all the column types --- ");
		for (int i = 1; i <= columnCount; i++) {
			String columnType = rsMetaData.getColumnTypeName(i);
			System.out.println("columnType" + i + " -> " + columnType);
		}

		// Close the connection and other objects
		rs.close();
		st.close();
		conn.close();
	}

}
