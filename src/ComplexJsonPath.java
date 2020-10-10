import files.payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonPath {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		
		JsonPath js= new JsonPath(payload.CoursePrice());
		//Print No of courses returned by API
		
	int count =	js.getInt("courses.size()"); // size is only on array
	System.out.println(count);
	
	// Print Purchase Amount
	
	int totalAmount = js.getInt("dashboard.purchaseAmount");
	System.out.println(totalAmount);
	
	//Print Title of the first course
	
	String title = js.get("courses[0].title");
	System.out.println(title);
	String title1 = js.get("courses[2].title");
	System.out.println(title1);
	
	//Print All course titles and their respective Prices , this will extract n no of courses
	
	for(int i =0; i<count; i++)
	{
		String courseTitles = js.get("courses["+i+"].title");
		//int price = js.get("courses["+i+"].price");
		System.out.println(courseTitles);
		System.out.println(js.get("courses["+i+"].price").toString()); // or syso always expect string , hence .ToString
		
	}
	
	
	System.out.println("Print no of copies sold by RPA Course");
	for(int i =0; i<count; i++)
	{
		String courseTitles = js.get("courses["+i+"].title");
		if(courseTitles.equalsIgnoreCase("RPA"))
		{
			// return copy sold
			int copycount = js.get("courses["+i+"].copies");
			System.out.println(copycount);
			break;
		}
		
		
	}
	
	

	}

}
