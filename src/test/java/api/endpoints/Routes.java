package api.endpoints;

/*
Swagger Petstore URL ==== > https://petstore.swagger.io/

Create user (POST)  : https://petstore.swagger.io/user
Get user (GET)      : https://petstore.swagger.io/user/{username}
Update user (PUT)   : https://petstore.swagger.io/user/{username}
Delete user (DELETE): https://petstore.swagger.io/user/{username}

 */

public class Routes {
	
	public static String base_url = "https://petstore.swagger.io/v2"; // available all over the project
	
	// User module URL
	public static String post_url = base_url + "/user";
	public static String get_url = base_url + "/user/{username}";
	public static String update_url = base_url + "/user/{username}";
	public static String delete_url = base_url + "/user/{username}";
	

	// Pet module URL
	public static String pet_post_url = base_url + "/pet";
	public static String pet_update_url = base_url + "/pet";
	public static String pet_get_url_by_status = base_url + "/pet/findByStatus";
	public static String pet_get_url_by_id = base_url + "/pet/{petId}";
	public static String pet_delete_url = base_url + "/user/{petId}";	
	
	
	// Store module URL
	
	
}
