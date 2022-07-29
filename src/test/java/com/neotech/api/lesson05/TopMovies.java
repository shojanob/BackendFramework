package com.neotech.api.lesson05;

import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class TopMovies {

	@Test
	public void compareMovies() {

		RestAssured.baseURI = "http://199.83.14.77:8080";

		Response top100Response = RestAssured.
				given().
					queryParam("count", "100").
				when().
					get("/api/v1/topMovies");

//		top100Response.prettyPrint();

		int size = top100Response.body().jsonPath().getList("").size();
//		System.out.println("size -> " + size);

		for (int i = 0; i < size; i++) {
			// I will make a get call for every iteration

			String id = top100Response.body().jsonPath().getString("[" + i + "].id");
			// System.out.println(id);
			int rank = top100Response.body().jsonPath().getInt("[" + i + "].rank");
			String title = top100Response.body().jsonPath().getString("[" + i + "].title");
			String releaseYear = top100Response.body().jsonPath().getString("[" + i + "].releaseYear");

			Response singleMovie = RestAssured.
					given().
						pathParam("id", id).
					when().
						get("/api/v1/movie/{id}");

			// singleMovie.prettyPrint();

			System.out.println("Comparing information of movie with rank -> " + rank);

			String singleId = singleMovie.body().jsonPath().getString("id");
			int singleRank = singleMovie.body().jsonPath().getInt("rank");
			String singleTitle = singleMovie.body().jsonPath().getString("title");
			String singleReleaseYear = singleMovie.body().jsonPath().getString("releaseYear");

			if (!id.equals(singleId)) {
				System.out.println("Ids are different! " + id + " != " + singleId);
			}

			if (rank != singleRank) {
				System.out.println("Ranks are different! " + rank + " != " + singleRank);
			}

			if (title == null || !title.equals(singleTitle)) {
				System.out.println("Titles are different! " + title + " != " + singleTitle);
			}

			if (!releaseYear.equals(singleReleaseYear)) {
				System.out.println("Release Years are different! " + releaseYear + " != " + singleReleaseYear);
			}

		}

	}

}
