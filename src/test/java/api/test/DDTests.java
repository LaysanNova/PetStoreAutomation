package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;
import api.utilities.DataProviders;

public class DDTests {
	
	@Test(priority=1, dataProvider="Data", dataProviderClass=DataProviders.class)
	public void testPosrUser(String userID, String uName, String fname, String lname, String uemail, String psw, String ph)
	{
		User userPayload = new User();
		
		userPayload.setId(Integer.parseInt(userID));
		userPayload.setUsername(uName);
		userPayload.setFirstname(fname);
		userPayload.setLastname(lname);
		
		userPayload.setEmail(uemail);
		userPayload.setPassword(psw);
		userPayload.setPhone(ph);		
		
		Response response = UserEndPoints.CreateUser(userPayload);
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	

	@Test(priority=2, dataProvider="UserNames", dataProviderClass=DataProviders.class)
	public void testGetUserByName(String uName)
	{

		Response response = UserEndPoints.ReadUser(uName);
		Assert.assertEquals(response.getStatusCode(), 200);
	}	
	
	
	@Test(priority=3, dataProvider="UserNames", dataProviderClass=DataProviders.class)
	public void testDeleteUserByName(String uName)
	{

		Response response = UserEndPoints.DeleteUser(uName);
		Assert.assertEquals(response.getStatusCode(), 200);
	}	
}
