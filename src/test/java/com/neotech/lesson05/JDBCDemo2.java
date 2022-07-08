package com.neotech.lesson05;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JDBCDemo2 {
	// Retrieve all the book category names and store them in ArrayList
	// Print the ArrayList in the console

	public static String dbUserName = "user1";
	public static String dbPassword = "Neotech@123";

	// jdbc:mysql://ipaddress:port/db_name
	public static String dbUrl = "jdbc:mysql://hrm.neotechacademy.com:3306/LibraryMgmt";

	public static void main(String[] args) throws SQLException {
		Connection conn = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);

		Statement st = conn.createStatement();

		ResultSet rs = st.executeQuery("SELECT * FROM bookcategory");

		List<String> categoryNames = new ArrayList<>();

		while (rs.next()) {
			categoryNames.add(rs.getObject("BookCategoryName").toString());
		}

		System.out.println(categoryNames);

		System.out.println("----------------");

		for (String name : categoryNames) {
			System.out.println(name);
		}

		// Closing the connection and other objects
		rs.close();
		st.close();
		conn.close();
	}

}
