package com.neotech.api.lesson05;

import org.junit.Test;

import com.neotech.utils.APIConstants;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class POST_GenerateToken {

	
	
	@Test
	public void generateToken()
	{
		RestAssured.baseURI = APIConstants.BASE_URI;
		
		String payload = "{\n" 
		+ "  \"userName\": \"Neotech_SB\",\r\n" 
		+ "  \"password\": \"Neotech@2022\"\r\n" 
		+ "}";
		
		Response generateTokenResponse = RestAssured.given()
		//.header("Content-Type", "application/json")
		.contentType(ContentType.JSON)
		.body(payload)
		.when()
		.post(APIConstants.GENERATE_TOKEN_ENDPOINT).prettyPeek();
		
		//verify the status code is 200
		generateTokenResponse.then().assertThat().statusCode(200);
		
		//to get the full status line
		System.out.println(generateTokenResponse.statusLine());
		
		//to get info from header
		System.out.println(generateTokenResponse.header("Content-Type"));
		
		
		//to get info from the body - we can use jsonPath().
		
		String token = generateTokenResponse.body().jsonPath().getString("token");
		
		System.out.println("Token: " + token);
		
		
		
		
		
		
	}
	
}
