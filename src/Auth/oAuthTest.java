package Auth;
import static io.restassured.RestAssured.*;


import io.restassured.path.json.JsonPath;

public class oAuthTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		// Need to setup
	/*	System.setProperties(("Webdriver,chrome.driver","chrome path ");
		WebDriver driver =new ChromeDriver();
		driver.get("https://accounts.google.com/o/oauth2/v2/auth/identifier?scope=https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&auth_url=https%3A%2F%2Faccounts.google.com%2Fo%2Foauth2%2Fv2%2Fauth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https%3A%2F%2Frahulshettyacademy.com%2FgetCourse.php&state=verifyfdss&flowName=GeneralOAuthFlow")
		driver.FindElement(by.cssSelector("input[type='email']")).sendKeys(Keys.ENTER)*/
		
		
		//https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php&state=verifyfdss
		
		
		String url = "WWWWW"; // copy from the browser 		
		String partialcode =url.split("code")[1];
		String code = partialcode.split("$scope")[0];
		System.out.println(code);
		
		
		String accessTokenResponse =   given().urlEncodingEnabled(false) // url false to % value will be encoded.
		.queryParams("code", "")
		.queryParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.queryParams("Client_Secret", "erzowm9g3utwnrj340yyak_w")
		.queryParams("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
		.queryParams("grant_type", "authorization_code")
		.when().log().all()
		.post("https://www.googleapis.com/oauth2/v4/token").asString();
		
		JsonPath js = new JsonPath(accessTokenResponse);
		String accessToken = js.getString("access_token");
				
		
		
		String response = given().queryParam("access_token", "accessToken")
		.when().log().all()
		.get("https://rahulshettyacademy.com/getCourse.php").asString();
		System.out.println(response);

	}

}
