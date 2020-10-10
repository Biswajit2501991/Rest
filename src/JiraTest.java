import static io.restassured.RestAssured.*;

import java.io.File;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;



public class JiraTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		// Create JIRA defect first
		RestAssured.baseURI="http://localhost:8080";
		
		SessionFilter session = new SessionFilter();
		
		
	/*	String response = given().header("Content-Type","application/json")
		.body("{ \"username\": \"Biswajitkumar01\", \"password\": \"Bishu@1991\" }")
		.log().all().filter(session).when().post("/rest/auth/1/session").then().log().all().extract().response().toString();
		System.out.println(response);
		
		given().log().all().header("Content-Type","application/json").header("JSESSIONID",session).body("{\r\n" + 
				"    \"fields\": {\r\n" + 
				"       \"project\":\r\n" + 
				"       {\r\n" + 
				"          \"key\": \"RES\"\r\n" + 
				"       },\r\n" + 
				"       \"summary\": \"REST ye merry gentlemen.\",\r\n" + 
				"       \"description\": \"This is new Creating of an issue using project keys and issue type names using the REST API\",\r\n" + 
				"       \"issuetype\": {\r\n" + 
				"          \"name\": \"Bug\"\r\n" + 
				"       }\r\n" + 
				"   }\r\n" + 
				"}")
				.filter(session)
				.when().post("/rest/api/2/issue").then().log().all().assertThat().statusCode(201);*/
	
		
		
		// Add comment to JIRA first
		String response2 = given().header("Content-Type","application/json")
		.body("{ \"username\": \"Biswajitkumar01\", \"password\": \"Bishu@1991\" }")
		.log().all().filter(session).when().post("/rest/auth/1/session").then().log().all().extract().response().toString();
		System.out.println(response2);
		
		
		String addCommentResponse = given().pathParam("key", "10004").log().all().header("Content-Type","application/json").header("JSESSIONID",session)
		.body("{\r\n" + 
				"    \"body\": \"This is my 2nd Comment.\",\r\n" + 
				"    \"visibility\": {\r\n" + 
				"        \"type\": \"role\",\r\n" + 
				"        \"value\": \"Administrators\"\r\n" + 
				"    }\r\n" + 
				"}")
				.filter(session)
				.when().post("rest/api/2/issue/{key}/comment").then().log().all().assertThat().statusCode(201)
				.extract().response().toString();
		
		JsonPath js =new JsonPath(addCommentResponse);
		String commentId = js.getString("id");
		System.out.println(commentId);
		
		
		// Add Attachment using multipart
		given().header("X-Atlassian-Token","no-check").filter(session).pathParam("key", "10004")
		.header("Content-Type","multipart/form-data")
		.multiPart("file",new File("C:\\Users\\Biswa\\Desktop\\DemoProject\\jira.txt")) // or new File("JIRA.text")
		.when()
		.post("/rest/api/2/issue/{key}/attachments")
		.then().log().all().assertThat().statusCode(200);
		
		
		// Get issue ( get request ) // Get the comment field
		
		String issueDetails = given().filter(session).pathParam("key", "10004")
				.queryParam("fields", "comment")
				.log().all()
				.when().get("/rest/api/2/issue/{key}")
				.then().log().all().extract().response().asString();
				System.out.println(issueDetails);
				
				JsonPath js1 = new JsonPath(issueDetails);
				int commentsCount = js1.getInt("fields.comment.comments.size()");
				for(int a=0;a<commentsCount; a++)
				{
					System.out.println(js1.getInt("fields.comment.comments["+a+"].id"));
				}
		
		
	}
	
	
	}
