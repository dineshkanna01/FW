package testcase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.internal.util.IOUtils;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PostRequest {
	
//	public static void main(String[] args) throws Exception {

//		@Test
		public void newTest() {

//		FileInputStream fi = new FileInputStream(new File("D:\\fw\\NewFramework\\src\\test\\resources\\jsonFiles\\RatePlan.xml"));

		RestAssured.baseURI="http://qa-igt-ttconnect.ttaws.com";
		
		String rBody="<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\r\n" + 
				"    <soap:Header/>\r\n" + 
				"    <soap:Body>\r\n" + 
				"        <OTA_HotelRateAmountNotifRQ Version=\"1\" EchoToken=\"1019173874\" TimeStamp=\"2020-08-18T12:52:20.0Z\" xmlns:schemalocation=\"http://www.opentravel.org/OTA/2003/05 OTA_HotelRateAmountNotifRQ.xsd\" xmlns=\"http://www.opentravel.org/OTA/2003/05\">\r\n" + 
				"            <UniqueID ID=\"1590555868\" Type=\"16\"/>\r\n" + 
				"            <RateAmountMessages ChainCode=\"UI\" HotelCode=\"qtest1\">\r\n" + 
				"                <RateAmountMessage>\r\n" + 
				"                    <StatusApplicationControl InvTypeCode=\"SRK\" IsRoom=\"1\" RatePlanCode=\"FRP2\"/>\r\n" + 
				"                    <Rates>\r\n" + 
				"                        <Rate Start=\"2021-12-25\" End=\"2021-12-26\" CurrencyCode=\"USD\" RateTimeUnit=\"Day\" UnitMultiplier=\"1\" Mon=\"1\" Tue=\"1\" Weds=\"1\" Thur=\"1\" Fri=\"1\" Sat=\"1\" Sun=\"1\">\r\n" + 
				"                            <BaseByGuestAmts>\r\n" + 
				"                                <BaseByGuestAmt AmountAfterTax=\"300.00\" CurrencyCode=\"USD\" NumberOfGuests=\"2\"/>\r\n" + 
				"                            </BaseByGuestAmts>\r\n" + 
				"                        </Rate>\r\n" + 
				"                    </Rates>\r\n" + 
				"                </RateAmountMessage>\r\n" + 
				"            </RateAmountMessages>\r\n" + 
				"        </OTA_HotelRateAmountNotifRQ>\r\n" + 
				"    </soap:Body>\r\n" + 
				"</soap:Envelope>";
		
		Response response = RestAssured.given()
				.contentType(ContentType.XML)
				.accept(ContentType.XML)
				.body(rBody).when().post("/services/soap/ota/2008a/HotelService/clid/availpro");


		int statusCode = response.statusCode();
		System.out.println("Status Code: "+statusCode);
		Assert.assertEquals(statusCode, 201);
		
	
		
	}
		
		@Test
		public void passFileAsPayload()
		{
			// Creating a File instance 
			File jsonDataInFile = new File("C:\\Users\\Dinesh.Kanna\\Dinesh\\FWAPI\\src\\main\\resources\\RateAmount.json");
			
			//GIVEN
			RestAssured
			    .given()
					.baseUri("http://qa-igt-ttconnect.ttaws.com/services/soap/ota/2008a/HotelService/clid/availpro")
					.contentType(ContentType.JSON)
					.body(jsonDataInFile)
			// WHEN
				.when()
					.post()
					// THEN
				.then()
					.assertThat()
					.statusCode(200);
//					.body("token", Matchers.notNullValue())
//					.body("token.length()", Matchers.is(15))
//					.body("token", Matchers.matchesRegex("^[a-z0-9]+$"));
		}

}
