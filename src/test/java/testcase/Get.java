package testcase;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import io.restassured.RestAssured;

public class Get extends TestBase{
	
	@BeforeClass
	public void setup() {
		RestAssured.useRelaxedHTTPSValidation();
		System.out.println("------------Execution Started------------");
	}
	
	@Test
	public void get() {
		getResponse();
	}
	
	@AfterClass
	public void TearDown() {
		System.out.println("------------Execution Ended------------");
	}
	

	
	
	
	
	
	
	
	
	
	
//	@Test
//	public void getBooking() {
//		int bookingID = RestAssured.
//				given().
//				baseUri("https://restful-booker.herokuapp.com/").
//				contentType("application/json").
//				when().
//				get("/booking").
//				then().
//				extract().
//				response().
//				path("booking[0]");
//		System.out.println("Booking ID: "+bookingID);
//		Assert.assertEquals(bookingID, 10);
//	}


}
