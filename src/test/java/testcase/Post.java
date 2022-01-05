package testcase;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import io.restassured.RestAssured;

public class Post extends TestBase{

	@BeforeClass
	public void setup() {
		RestAssured.useRelaxedHTTPSValidation();
		System.out.println("------------Execution Started------------");
	}
	
	@Test
	public void post() {
		TryResponse();
	}
	
	@AfterClass
	public void TearDown() {
		System.out.println("------------Execution Ended------------");
	}
}
