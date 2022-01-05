package logging;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class PutTestLogging {
	
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
				" \"name\": \"Dineshkanna\",\r\n" +
				" \"salary\": \7653267\",\r\n" +
				" \"age\": \"40\"\r\n" +
				"}")

		.when()
		.log()
		.body()
		.put("https://dummy.restapiexample.com/public/api/v1/update/2021").statusLine(); 

	}

}
