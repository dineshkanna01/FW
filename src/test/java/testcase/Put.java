package testcase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import Utility.ConfigManager;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.internal.util.IOUtils;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Put {
	static String BASE_URL = ConfigManager.getInstance().getString("base_url");
//public static void main(String[] args) throws Exception {
//	
//	FileInputStream fi = new FileInputStream(new File(".\\resources\\RateAmount.json"));
//	
////	byte[] in = Files.readAllBytes(Paths.get("\\resources\\RateAmount.json"));
////	String input=new String(in);
//	
//	
//	
//	RestAssured.baseURI=BASE_URL;
//	RequestSpecification httpRequest = RestAssured.given();
//
//	httpRequest.header("Content-Type", "application/json");
//
//	httpRequest.body(IOUtils.toByteArray(fi));
//	
//
//	Response response = httpRequest.request(Method.POST,"/create");
//
//	String responseBody = response.getBody().toString();
//	System.out.println("Respose Body is: " +responseBody);
//	
//	System.out.println("Response Body: "+response.getBody().asString());
//	Assert.assertEquals(responseBody.contains("success"), false);
//	
//	int statusCode = response.statusCode();
//	System.out.println("Status Code: "+statusCode);
//	Assert.assertEquals(statusCode, 200);
//}

	@Test
    public void xmlPostRequest_Test() {
 
        RestAssured.baseURI = "http://localhost:8006";
 
        String requestBody = "<client>\r\n" +
            "    <clientNo>100</clientNo>\r\n" +
            "    <name>Tom Cruise</name>\r\n" +
            "    <ssn>124-542-5555</ssn>\r\n" +
            "</client>";
 
        Response response = null;
        
        response = given().
        contentType(ContentType.XML)
            .accept(ContentType.XML)
            .body(requestBody)
            .when()
            .post("/addClient");
 
        System.out.println("Post Response :" + response.asString());
        System.out.println("Status Code :" + response.getStatusCode());
        System.out.println("Does Reponse contains '100 Tom Cruise 124-542-5555'? :" + response.asString().contains("100 Tom Cruise 124-542-5555"));
    }
private RequestSpecification given() {
	// TODO Auto-generated method stub
	return null;
}
}
