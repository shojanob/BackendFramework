package com.neotech.api.lesson05;

import org.junit.Assert;
import org.junit.Test;

import com.neotech.utils.APIConstants;
import com.neotech.utils.APIGlobalVariables;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.*;



public class PUT_UpdateOneBook {

	@Test
	public void updateOneBook()
	{
		
		String payload = "{\r\n"
				+ "  \"userId\": \""+APIGlobalVariables.userID + "\",\r\n"
				+ "  \"isbn\": \""+ APIGlobalVariables.book1 + "\"\r\n"
				+ "}";
		
	//	System.out.println(payload);
		
		RestAssured.baseURI = APIConstants.BASE_URI;
		
		/*
		 * RestAssured.given() .header("Authorization",
		 * "Bearer "+APIGlobalVariables.token) .pathParam("ISBN",
		 * APIGlobalVariables.book2) .contentType(ContentType.JSON) .body(payload)
		 * .when() .put(APIConstants.UPDATE_ONE_BOOK_ENDPOINT) .prettyPeek() .then()
		 * .assertThat().statusCode(200);
		 */
	
		//2nd way - to check for the added book
		Response response = RestAssured.given()
		.header("Authorization", "Bearer "+APIGlobalVariables.token)
		.pathParam("ISBN", APIGlobalVariables.book2)
		.contentType(ContentType.JSON)
		.body(payload)
		.when()
		.put(APIConstants.UPDATE_ONE_BOOK_ENDPOINT)
		.prettyPeek();

		
		//then I can validate
		
		response.then().assertThat().statusCode(200);
		
		boolean containsString = response.jsonPath().getString("books").contains(APIGlobalVariables.book1);
		
		Assert.assertTrue(containsString);
		
		
		
	}
	
	
}
