package com.neotech.api.lesson05;

import org.junit.Test;

import com.neotech.utils.APIConstants;
import com.neotech.utils.APIGlobalVariables;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.*;


public class POST_AddListOfBooks {


	@Test
	public void addListOfBooks()
	{
		String payload = "{\r\n"
				+ "  \"userId\": \""+APIGlobalVariables.userID +"\",\r\n"
				+ "  \"collectionOfIsbns\": [\r\n"
				+ "    {\r\n"
				+ "      \"isbn\": \""+APIGlobalVariables.book1 +"\"\r\n"
				+ "    },\r\n"
				+ "    {\r\n"
				+ "      \"isbn\": \""+APIGlobalVariables.book2 +"\"\r\n"
				+ "    }\r\n"
				+ "  ]\r\n"
				+ "}";
		
	//	System.out.println(payload);
		
		RestAssured.baseURI = APIConstants.BASE_URI;
		
		RestAssured.given()
		.auth().oauth2(APIGlobalVariables.token)
		.body(payload)
		.contentType(ContentType.JSON)
		.when()
		.post(APIConstants.ADD_LIST_OF_BOOKS_ENDPOINT)
		.prettyPeek()
		.then()
		.assertThat().statusCode(201)
		.and()
		.body("books[0].isbn", is(APIGlobalVariables.book1))
		.and()
		.body("books[1].isbn", is(APIGlobalVariables.book2));
		
	}
}
