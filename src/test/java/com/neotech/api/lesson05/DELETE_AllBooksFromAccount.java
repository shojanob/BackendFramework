package com.neotech.api.lesson05;

import org.hamcrest.Matchers;
import org.junit.Test;

import com.neotech.utils.APIConstants;
import com.neotech.utils.APIGlobalVariables;

import io.restassured.RestAssured;

public class DELETE_AllBooksFromAccount {

	@Test
	public void deleteAllBooks()
	{
		RestAssured.baseURI = APIConstants.BASE_URI;
		
		RestAssured
			.given()
					.auth().oauth2(APIGlobalVariables.token)
					.queryParam("UserId",APIGlobalVariables.userID)
			.when()
					.delete(APIConstants.DELETE_ALL_BOOKS_ENDPOINT)
					.prettyPeek()
			.then()
					.assertThat().statusCode(204)
			.and()
					.assertThat().body("userId", Matchers.equalTo(APIGlobalVariables.userID));
		//this assertion failed, even though the books are deleted
		
		
		
		
	}
}
