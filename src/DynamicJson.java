import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.ReUsableMethods;
import files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class DynamicJson {
	
	@Test(dataProvider="BooksData")
	public void addbook(String isbn , String aisle)
	{
		//Post Addbook
		RestAssured.baseURI="http://216.10.245.166"; // base url
		String response = given().header("Content-Type","application/json")
		.body(payload.Addbook(isbn,aisle))
		.when()
		.post("Library/Addbook.php")
		.then().assertThat().statusCode(200)
		.extract().response().asString();
		JsonPath js = ReUsableMethods.rawToJson(response);
		String Id = js.get("ID");
		System.out.println(Id);
		
		// Delete Book
		
		
	}
	
	@DataProvider(name="BooksData") // just assign name to data provider
	public Object[][] getData()
	{
		return new Object[][] {{"new1","4575"}, {"new2","1541"}, {"new3","1542"}};
	}
	

	
	
	

}
