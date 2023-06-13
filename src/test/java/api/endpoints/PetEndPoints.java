package api.endpoints;

import static io.restassured.RestAssured.given;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PetEndPoints {
	
	public static Response CreatePet(Pet payload)
	{
		Response response = given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
		.when()
			.post(Routes.pet_post_url);
			
		return response;	
	}


	public static Response FindPetById(int petId)
	{
		Response response = given()
			.pathParam("username", petId)
		.when()
			.get(Routes.pet_get_url_by_id);
		
		return response;	
	}	

	public static Response UpdatePet(int petId, User payload)
	{
		Response response = given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
		.when()
			.put(Routes.pet_update_url);
			
		return response;	
	}





}
