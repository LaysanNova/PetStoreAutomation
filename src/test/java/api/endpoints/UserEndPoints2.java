package api.endpoints;

import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


// UserEndPoints.java
// Created for perform Create, Read, Update, Delete the user API

public class UserEndPoints2 {
	
	// method created for getting URL's from properties file
	static ResourceBundle getURL()
	{
		ResourceBundle routes = ResourceBundle.getBundle("routes"); // load property file
		return routes;
	}
	
		
	public static Response CreateUser(User payload)
	{
		
		String post_url = getURL().getString("post_url");
			
		Response response = given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
		.when()
			.post(post_url);
			
		return response;	
	}
	
	public static Response ReadUser(String username)
	{
		String get_url = getURL().getString("get_url");
		
		Response response = given()
			.pathParam("username", username)
		.when()
			.get(get_url);
		
		return response;	
	}	

	public static Response UpdateUser(String username, User payload)
	{
		String update_url = getURL().getString("update_url");
		
		Response response = given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.pathParam("username", username)
			.body(payload)
		.when()
			.put(update_url);
			
		return response;	
	}
	
	public static Response DeleteUser(String username)
	{
		String delete_url = getURL().getString("delete_url");
		
		Response response = given()
			.pathParam("username", username)
		.when()
			.delete(delete_url);
		
		return response;	
	}	
}
