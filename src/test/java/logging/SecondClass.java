package logging;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test; 
import io.restassured.RestAssured;
import io.restassured.http.ContentType; 

public class SecondClass {
	
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
				" \"name\": \"vedio version 11\",\r\n" +
				" \"description\": \r rest methods example versions 11\",\r\n" +
				" \"price\": \"1000\"\r\n" +
				"}")

		.when()
		.log().body()
		.put("https://chercher.tech/sample/api/product/create"); 



	}

}
