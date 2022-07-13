package com.neotech.lesson06;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.neotech.utils.DBUtils;

public class StoringDataWithUtils {

	@Test
	public void getDataFromDB() {
		// create connection
		DBUtils.getConnection();

		// execute a query and get the list of maps
		List<Map<String, String>> lm = DBUtils.storeDataFromDB("SELECT * FROM employees LIMIT 5");
		System.out.println(lm);
		
		
		//close the connection
		DBUtils.closeConnection();
	}

}
