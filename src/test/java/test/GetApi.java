package test;

import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.TestBase;
import client.RestClient;
import io.restassured.RestAssured;

public class GetApi extends TestBase{
	
	TestBase testBase;
	String serviceUrl;
	String apiUrl;
	String url;
	RestClient restClient;
	
	
	@BeforeMethod
	public void setup() {
		RestAssured.useRelaxedHTTPSValidation();
		testBase = new TestBase();
		
		serviceUrl = prop.getProperty("url");
		apiUrl = prop.getProperty("serviceUrl");
		
		url = serviceUrl+apiUrl;
		
		
	}
	
	@Test
	public void getAPITest() throws IOException, Exception {
		restClient= new RestClient();
		restClient.get(url);
	}

}
