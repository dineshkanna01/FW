package logging;


import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import io.restassured.RestAssured;

public class GetTestLogging extends TestBase{
	
	@BeforeClass
	public void setup() {
	RestAssured.useRelaxedHTTPSValidation();
	}
	
	@Description("Verify the Get request Using Logging...")
	@Severity(SeverityLevel.MINOR)
	@Epic("EP01")
	@Feature("Feature2: Get request Using Logging.")
	@Story("Story: Get request Using Logging.")
	@Step("Verify Get request Using Logging.")
	@Test
	void testLogging() {
		
		RestAssured.given()
		.when()
		.log()
		.all()
		.get("http://dummy.restapiexample.com/api/v1/employees");
		
		System.out.println("===============>After receiving response<==================");
		
		RestAssured.given()
		.when()
		.get("http://dummy.restapiexample.com/api/v1/employees")
		.then()
		.log()
		.all();
	}
	
	@AfterClass
	public void report() throws Exception {
		cmdPrompt();
	}

}
