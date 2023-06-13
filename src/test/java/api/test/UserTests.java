package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class UserTests {
	
	Faker faker;
	User userPayload;
	
	public Logger logger;  //for logs
	
	@BeforeClass
	public void setup()
	{
		
		faker = new Faker();
		userPayload = new User();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstname(faker.name().firstName());
		userPayload.setLastname(faker.name().lastName());
		
		userPayload.setEmail(faker.internet().emailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
	
		// logs
		logger = LogManager.getLogger(this.getClass());		
		logger.debug("debugging.......");
		
	}
	
	@Test(priority=1)
	public void TestPostUser()
	{	
		logger.info("**************** Creating user *************************");
		Response response = UserEndPoints.CreateUser(userPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.header("access-control-allow-headers"), "Content-Type, api_key, Authorization");
		Assert.assertEquals(response.header("access-control-allow-methods"), "GET, POST, DELETE, PUT");
		Assert.assertEquals(response.header("access-control-allow-origin"), "*");
		Assert.assertEquals(response.header("content-type"), "application/json");
		Assert.assertEquals(response.header("server"), "Jetty(9.2.9.v20150224)");
		// Assert.assertEquals(response.header("date"), "format of date");

		String bodycode = response.jsonPath().get("code").toString();
		Assert.assertEquals(bodycode, "200");
		
		String bodytype = response.jsonPath().get("type").toString();
		Assert.assertEquals(bodytype, "unknown");
		
		String bodymessage = response.jsonPath().get("message").toString();
		//Assert.assertEquals(bodymessage, "1020155847");
	
		
		logger.info("**************** User is created *************************");
	}
	
	
	@Test(priority=2)
	public void testGetUserByName()
	{
		logger.info("**************** Reading user info *************************");
		Response response = UserEndPoints.ReadUser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		
		
		
		logger.info("**************** User info is displayed  *************************");
	}
	
	@Test(priority=3)
	public void testUpdateUserBeName()
	{
		logger.info("**************** Updating user info *************************");
		//update data using payload
		
		userPayload.setEmail(faker.internet().emailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setUserstatus(1);
		
		Response response = UserEndPoints.UpdateUser(this.userPayload.getUsername(), userPayload);
		
		response.then().log().all();
		
		response.then().log().body();
		
		// Two ways of Status Code validation
//		response.then().log().body().statusCode(200); 		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("**************** User is updated *************************");
		
		// Checking data after u8pdating
		Response responseAfterUpdate = UserEndPoints.ReadUser(this.userPayload.getUsername());
		Assert.assertEquals(response.getStatusCode(), 200);		

	}
	
	@Test(priority=4)
	public void testDeleteUserByName()
	{
		logger.info("**************** Deleting user *************************");
		Response response = UserEndPoints.DeleteUser(this.userPayload.getUsername());
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("**************** User deleted *************************");
	}
		
}
