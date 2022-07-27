package com.neotech.api.lesson05;

import org.junit.Test;

import com.neotech.utils.APIConstants;
import com.neotech.utils.APIGlobalVariables;
import io.restassured.RestAssured;

import static org.hamcrest.Matchers.*;

public class GET_Account {

	
	@Test
	public void getAccount()
	{
		RestAssured.baseURI = APIConstants.BASE_URI;
		
		RestAssured.given()
		.header("Authorization", "Bearer " + APIGlobalVariables.token)
//		.auth().oauth2(APIGlobalVariables.token)
		.pathParam("UUID", APIGlobalVariables.userID)
		.when()
		.get(APIConstants.GET_ACCOUNT_ENDPOINT).prettyPeek()
		.then()
		.assertThat().statusCode(200)
		.and()
		.body("userId", is(APIGlobalVariables.userID))
		.and()
		.body("username", equalTo("Neotech_SB"));
		
	}
	
	
	
	
}
