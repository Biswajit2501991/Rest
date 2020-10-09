import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import files.payload;

public class basics {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		// validate if Add place API is working as expecting 
		
		// given, - all input details 
		//when, - submit the API
		//then- Validation the response
		

		
			RestAssured.baseURI = "https://rahulshettyacademy.com/";
			
			 String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
			.body(payload.AddPlace()).when().post("maps/api/place/add/json")
			.then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP"))
			.header("server", "Apache/2.4.18 (Ubuntu)").extract().response().asString();
			 
			 System.out.println(response);
			 // Getting place id for further use
			 JsonPath js = new JsonPath(response); // for parsing Json
			 String placeId = js.getString("place_id");
			 
			 System.out.println(placeId);
			 
			 
			//Update place
			 
			 String newAddress = "Summer Walk, Africa";
			 given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
			 .body("{\r\n" + 
			 		"\"place_id\":\""+placeId+"\",\r\n" + 
			 		"\"address\":\""+newAddress+"\",\r\n" + 
			 		"\"key\":\"qaclick123\"\r\n" + 
			 		"}")
			 .when().put("maps/api/place/update/json")
			 .then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));
			
		
			 // Get to validate if address is update
			
		String getPlaceResponse	= given().log().all().queryParam("Key", "qaclick123")
			 .queryParam("place_id", placeId)
			 .when().get("maps/api/place/get/json")
			 .then().assertThat().log().all().statusCode(200).extract().response().asString();
			 
		JsonPath Js1 = new JsonPath(getPlaceResponse);
		String actualAddress = Js1.getString("address");
		System.out.println(actualAddress);
		Assert.assertEquals(actualAddress, newAddress);
		
			 
			 
			 
			
			
		}
}