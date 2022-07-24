package com.neotech.api.lesson04;


import org.junit.Assert;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;



public class ResponseVerification {

	@Test
	public void verifyAllBooksRequest() {

		RestAssured.baseURI = "https://bookstore.toolsqa.com";
		
		RequestSpecification getAllBooksRequest = RestAssured.given();
		
		Response response = getAllBooksRequest.when().get("/BookStore/v1/Books");
		
		System.out.println("-----------------");

		//verify the response code is 200
		response.then().assertThat().statusCode(200);
		
		//using JUnit assertion
		int actualStatusCode = response.statusCode();
		Assert.assertEquals(200, actualStatusCode);
		
		System.out.println("-----------------");
		
		//verify the header information
		System.out.println("Verify that Content-Type is ...");
		
		
		//we can either the the value and then user the JUnit assertion
//		String contentType = response.getHeader("Content-Type");
//		Assert.assertEquals("....", contentType);

		//or we can user assertThat()
		response.then().assertThat().header("Content-Type", "application/json; charset=utf-8");
		
		System.out.println("-----------------");
		
		System.out.println("Verify that the body contains Git Pocket Guide");
		
		String body = response.body().asString();
		
		boolean contains = body.contains("Git Pocket Guide");
		
		Assert.assertTrue(contains);
		
		
		//one liner
	//	Assert.assertTrue(response.body().asString().contains("Git Pocket Guide"));
		
	}

}
