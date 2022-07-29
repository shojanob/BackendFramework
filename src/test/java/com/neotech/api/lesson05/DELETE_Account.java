package com.neotech.api.lesson05;

import org.junit.Test;

import com.neotech.utils.APIConstants;
import com.neotech.utils.APIGlobalVariables;

import io.restassured.RestAssured;

public class DELETE_Account {
	
	@Test
	public void deleteAccountTest() {
		
		RestAssured.baseURI = APIConstants.BASE_URI;
		
		RestAssured.
			given().
				auth().oauth2(APIGlobalVariables.token).
				pathParam("UUID", APIGlobalVariables.userID).
			when().
				delete(APIConstants.DELETE_ACCOUNT_ENDPOINT).prettyPeek().
			then().
				assertThat().statusCode(204);
		
	}

}
