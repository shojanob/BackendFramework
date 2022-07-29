package com.neotech.api.lesson05;

import org.junit.Test;

import com.neotech.utils.APIConstants;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class POST_CreateNewUser {

	@Test
	public void createUserTest() {

		String payload = "{\n" 
		+ "  \"userName\": \"Neotech_EL\",\r\n" 
		+ "  \"password\": \"Neotech@2022\"\r\n" 
		+ "}";

		RestAssured.baseURI = APIConstants.BASE_URI;
		
		//1st way
		RestAssured.given()
		.header("Content-Type", "application/json")
		.body(payload)
		.when().post(APIConstants.CREATE_NEW_ACCOUNT_ENDPOINT).prettyPeek()
		.then().assertThat().statusCode(201);
		
		//2nd way
		/*
		 * Response response = RestAssured.given() .header("Content-Type",
		 * "application/json") .body(payload) .when().post("/Account/v1/User");
		 * 
		 * response.prettyPrint();
		 * 
		 * response.then().assertThat().statusCode(201);
		 * 
		 */		
		
	}
	
	
	
	
}
