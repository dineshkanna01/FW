package logging;


import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class FirstTest {
	
	@BeforeClass
	public void setup() {
	RestAssured.useRelaxedHTTPSValidation();
	}
	
	@Test
	void testLogging() {
		RestAssured.given()
		.when()
		.log()
		.all()
		.get("https://reqres.in/api/users?page=2");
		
		System.out.println("after receiving");
		
		RestAssured.given()
		.when()
		.get("https://reqres.in/api/users?page=2")
		.then()
		.log()
		.all();
	}

}
