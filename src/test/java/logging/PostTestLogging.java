package logging;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test; 
import io.restassured.RestAssured;
import io.restassured.http.ContentType; 

public class PostTestLogging {
	
	@BeforeClass
	public void setup() {
		RestAssured.useRelaxedHTTPSValidation();
	}
	
	@Test
	void testLogging() {
		
		RestAssured.given().
		contentType(ContentType.JSON).
		body("\r\n" +
				"{\r\n" +
				" \"id\": \"2021\"\r\n" +
				" \"name\": \"Dkanna\",\r\n" +
				" \"salary\": \765367\",\r\n" +
				" \"age\": \"20\"\r\n" +
				"}")

		.when()
		.log()
		.body()
		.post("https://dummy.restapiexample.com/api/v1/create");

	}

}
