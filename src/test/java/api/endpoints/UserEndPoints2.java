package api.endpoints;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.Properties;
import java.util.ResourceBundle;

import api.payload.User;
import api.utils.PropertyReader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndPoints2 {

	public static PropertyReader propReader;

	/*
	 * public static String getUrlFromRoutesPropFile() throws IOException {
	 * 
	 * Alternate way to read properties file
	 * 
	 * ResourceBundle routes = ResourceBundle.getBundle("routes");
	 * 
	 * return routes;
	 * 
	 * propReader =new PropertyReader(); return propReader.getPostUrl();
	 * 
	 * }
	 */

	public static Response createUser(User payload) throws IOException {

		propReader = new PropertyReader();
		String post_url = propReader.getPostUrl();

		System.out.println("POST URL :" + post_url);

		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(payload).when()
				.post(post_url).andReturn();

		return response;

	}

	public static Response getUser(String username) throws IOException {

		propReader = new PropertyReader();
		String get_url = propReader.getGetUrl();


		System.out.println("GET URL :" + get_url);

		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON)
				.pathParam("username", username).when().get(get_url).andReturn();

		return response;
	}

	public static Response updateUser(String username, User payload) throws IOException {

		propReader = new PropertyReader();
		String put_url = propReader.getPutUrl();

		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON)
				.pathParam("username", username).body(payload).when().put(put_url).andReturn();

		return response;
	}

	public static Response deleteUser(String username) throws IOException {

		propReader = new PropertyReader();
		String delete_url = propReader.getDeleteUrl();

		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON)
				.pathParam("username", username).when().delete(delete_url).andReturn();

		return response;
	}

}
