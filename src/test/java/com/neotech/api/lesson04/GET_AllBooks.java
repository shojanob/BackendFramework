package com.neotech.api.lesson04;

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class GET_AllBooks {

	public static void main(String[] args) {

		// Base URI for all the API calls
		RestAssured.baseURI = "https://bookstore.toolsqa.com";

		System.out.println(RestAssured.baseURI);

		// building a request (preparing, not sent yet)
		RequestSpecification getAllBooksRequest = RestAssured.given();

		// make this actual request to the server and get the response
		Response allBooksResponse = getAllBooksRequest.when().get("/BookStore/v1/Books");

		// another way how we can send a request
//		Response allBooksResponse =	getAllBooksRequest.when().request(Method.GET,"/BookStore/v1/Books");

		System.out.println("---------------------");

		// information inside the response object
		int statusCode = allBooksResponse.getStatusCode();
		// OR we can use int statusCode = allBooksResponse.statusCode();
		System.out.println("The status code is: " + statusCode);

		System.out.println("---------------------");

		// getting headers from the response object

		Headers headers = allBooksResponse.getHeaders();
		System.out.println(headers);

		System.out.println("---------------------");

		// getting a particular header
		String contentType = allBooksResponse.getHeader("Content-Type");
		System.out.println(contentType);

		System.out.println("---------------------");

		ResponseBody body = allBooksResponse.getBody();
		System.out.println(body.asString());
		System.out.println("---------------------");

		// display the response body in a pretty format
		allBooksResponse.prettyPrint();

	}

}
