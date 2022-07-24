package com.neotech.api.lesson04;

import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class POST_CreateUser {

	@Test
	public void createUserTest() {
		RestAssured.baseURI = "https://bookstore.toolsqa.com";

		// we create a request
		RequestSpecification createUserRequest = RestAssured.given();

		// we can modify header
		createUserRequest.header("Content-Type", "application/json");

		// we can also modify the body
		String payload = "{\n" + "  \"userName\": \"Neotech2022\",\r\n" + "  \"password\": \"Neotech@2022\"\r\n" + "}";

		System.out.println(payload);
		// we are adding the payload to the body of the request
		createUserRequest.body(payload);

		// we can now make a POST call
		Response response = createUserRequest.when().post("/Account/v1/User");

		// we can also do it this way
		// Response response = createUserRequest.when().request(Method.POST,
		// "/Account/v1/User");

		System.out.println("---------------");
		System.out.println("Status Code is: " + response.statusCode());

		System.out.println("---------------");
		System.out.println(response.getStatusLine());

		System.out.println("---------------");
		response.prettyPrint();

	}

	@Test
	public void createUserTestShortWay() {

		String payload = "{\n" 
		+ "  \"userName\": \"Neotech2022\",\r\n" 
		+ "  \"password\": \"Neotech@2022\"\r\n" 
		+ "}";

		RestAssured.baseURI = "https://bookstore.toolsqa.com";
		
		//method chaining
		RestAssured.given()
		.header("Content-Type", "application/json")
		.body(payload)
		.when().post("/Account/v1/User")
		.prettyPrint();
		
		
		
	}

}
