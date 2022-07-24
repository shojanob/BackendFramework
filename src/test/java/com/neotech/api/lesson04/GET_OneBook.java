package com.neotech.api.lesson04;

import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static org.hamcrest.Matchers.*; //importing all static methods in that location
//import org.hamcrest.Matchers;

public class GET_OneBook {

	@Test
	public void getOneBookTest() {
		RestAssured.baseURI = "https://bookstore.toolsqa.com";

		RequestSpecification getOneBookRequest = RestAssured.given();

		// during the request specification, before we make a call we have to complete
		// any
		// necessary addition information
		getOneBookRequest.queryParam("ISBN", "9781491950296");

		// making the call
		Response response = getOneBookRequest.when().get("/BookStore/v1/Book");

		// another way to make a call with query parameters
//	Response response = getOneBookRequest.when().get("/BookStore/v1/Book?ISBN=9781491950296");

		System.out.println("--------------------");

		System.out.println("Status code is: " + response.statusCode());

		response.then().assertThat().statusCode(200);

		System.out.println("--------------------");

		response.prettyPrint();

		// assertions using the Hamcrest library

		response.then().assertThat().body("title", equalTo("Programming JavaScript Applications"));

		response.then().assertThat().body("subTitle", containsString("Architecture"));

		response.then().assertThat().body("author", endsWith("Elliott"));

	}

}
