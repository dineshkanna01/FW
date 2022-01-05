package base;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.json.simple.JSONObject;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import Utility.ConfigManager;
import Utility.TestUtils;
import cmdprompt.SyncPipe;
import helper.JsonHelper;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class TestBase {

	public static WebDriver driver;
	public static Properties prop; 

	public TestBase() {
		try {
			prop = new Properties();
			FileInputStream ip=new FileInputStream("C:\\Users\\Dinesh.Kanna\\eclipse-workspace\\IGT\\src\\main\\java\\config\\Configuration.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void initilization() {
		String browserName = prop.getProperty("browser");
		if (browserName.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
		}
		else if(browserName.equals("Firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		}else if(browserName.equals("ED")) {
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
		}else {
			System.out.println("No browser");
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(TestUtils.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtils.IMPLICITWAIT, TimeUnit.SECONDS);

		driver.get(prop.getProperty("url"));
	}
	
	public static void initilizationmultibrowser(String browserName) {
		if (browserName.equals("chrome")) {
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		}
		else if(browserName.equals("Firefox")) {
		WebDriverManager.firefoxdriver().setup();
		driver=new FirefoxDriver();
		}else if(browserName.equals("edge")) {
		WebDriverManager.edgedriver().setup();
		driver=new EdgeDriver();
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(TestUtils.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtils.IMPLICITWAIT, TimeUnit.SECONDS);

		}

	public static void screenShot(String fileName) {
		prop = new Properties();
		File file=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(file, new File("ScreenshotPath/"+fileName+".jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public static void cmdPrompt() {
		String [] command = {"cmd"};
		Process p;
		try {
			p=Runtime.getRuntime().exec(command);
			
			new Thread(new SyncPipe(p.getErrorStream(), System.err)).start();
			new Thread(new SyncPipe(p.getInputStream(), System.err)).start();
			PrintWriter stdin = new PrintWriter(p.getOutputStream());
			stdin.println("allure serve C:\\Users\\Dinesh.Kanna\\eclipse-workspace\\IGT\\allure-results");
			
			stdin.close();
			try {
				p.waitFor();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void mail() throws Exception {
		Email email = new SimpleEmail();
		email.setHostName("smtp.gmail.com");
		email.setSmtpPort(465);
		email.setAuthenticator(new DefaultAuthenticator("sekardineshkanna@gmail.com", "Disser01"));
		email.setSSLOnConnect(true);
		email.setFrom("sekardineshkanna@gmail.com");
		email.setSubject("TestMail");
		email.setMsg("This is a test mail ... :-)");
		email.addTo("sekardineshkanna@gmail.com");
		email.send();
	}
	
//	public static void jdbc() throws Exception {
//		Class.forName("com.mysql.jdbc.Driver");
//		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sample","root","8080");
//		
//		Statement st = con.createStatement();
//		ResultSet rs = st.executeQuery("select*from Employees");
//		
//		while (rs.next()) {
//			int s = rs.getInt("S.no");
//			String name = rs.getString("username");
//			String pass = rs.getString("password");
//			System.out.println(name);
//			System.out.println(pass);
//		}
//		st.close();
//		con.close();
//	}
	
	public static void sendingMail() {
		Runtime r = Runtime.getRuntime();
//		r.addShutdownHook(new Thread());
		Mail m= new Mail();
		try {
			m.mail();
			System.out.println("Report sent");
		} catch (Exception e) {
			e.printStackTrace();
		}
//		try {
//			Thread.sleep(6000);
//		} catch (Exception e) {
//			System.out.println(e);
//		}
	

	}
	
	
	public static Connection con() {
		try {
			 String JDBC_Driver = "com.mysql.jdbc.Driver";
			 String urlDB = "jdbc:mysql://localhost:3306/DBname";
			 String user = "dineshkanna";
			 String pass = "123456";
			 Class.forName(JDBC_Driver);
			 Connection connect=DriverManager.getConnection(urlDB, user, pass);
			 return connect;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
		
	}
	
	public static ArrayList<String> select(String query) throws Exception  {
		Connection conn = con();
		PreparedStatement statement = conn.prepareStatement(query);
		
		ResultSet result = statement.executeQuery();
		ArrayList<String> array = new ArrayList<String>();
		while (result.next()) {
			array.add(result.getString("url"));
		}
		return array;
		
	}
	
	
	public static String jsonFile(String User, String jsonLocation, String value) throws Exception {
		JsonHelper helper=new JsonHelper();
		Map<String,String> map=new HashMap<String,String>();
		
		map=helper.readJsonFile(User,jsonLocation);
		System.out.println(map);
		System.out.println(map.get(value));
//		System.out.println(map.get("AadharNumber"));
//		System.out.println(map.get("DateofBirth"));
//		
//		map=helper.readJsonFile("addUser1","UserManagementTest.json");
//		System.out.println(map);
//		System.out.println(map.get("userName"));
//		System.out.println(map.get("AadharNumber"));
//		System.out.println(map.get("DateofBirth"));
		return value;
	}
	
//	public static void getResponse() {
	//
	//		RestAssured.baseURI=BASE_URL;
	//
	//		RequestSpecification httpRequest = RestAssured.given();
	//		Response response = httpRequest.request(Method.GET,"/services/soap/ota/2008a/HotelService/clid/availpro");
	//
	//		System.out.println("======Body======");
	//		String responseBody = response.getBody().asString();
	//		System.out.println("Respose Body is: " +responseBody);
	//		Assert.assertEquals(responseBody.contains("success"), true);
	//
	//		int statusCode = response.statusCode();
	//		System.out.println("Status Code: "+statusCode);
	//		Assert.assertEquals(statusCode, 200);
	//
	//		System.out.println("*******Headers*******");
	//		Headers allheaders = response.headers();
	//		for (Header header : allheaders) {
	//			System.out.println(header.getName()+"  :  "+header.getValue());
	//		}
	//
	//		String statusLine = response.getStatusLine();
	//		System.out.println("Status Code  is:  "+statusLine);
	//		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	//
	//		Response res = RestAssured.get("http://dummy.restapiexample.com/api/v1/employees");
	//		System.out.println("Response : "+res);
	//		System.out.println("Response Body: "+res.asString());
	//		System.out.println("Response Body: "+res.getBody().asString());
	//		System.out.println("Status code: "+res.getStatusCode());
	//		System.out.println("Status: "+res.getStatusLine());
	//		System.out.println("Header: "+res.getHeader("content-type"));
	//		System.out.println("RunTime: "+res.getTime());
	//	}

	//	public static void PostResponse() {
	//		RestAssured.baseURI=BASE_URL;
	//		RequestSpecification httpRequest = RestAssured.given();
	//		JSONObject requestParam = new JSONObject();
	//
	//		//			requestParam.put("name", "dk02");
	//		//			requestParam.put("salary", "66666");
	//		//			requestParam.put("age", "28");
	//
	//		httpRequest.header("Content-Type", "application/json");
	//
	//		httpRequest.body(requestParam.toJSONString());
	//
	//		Response response = httpRequest.request(Method.POST,"/services/soap/ota/2008a/HotelService/clid/availpro");
	//
	//		String responseBody = response.getBody().toString();
	//		System.out.println("Respose Body is: " +responseBody);
	//
	//		System.out.println("Response Body: "+response.getBody().asString());
	//		Assert.assertEquals(responseBody.contains("success"), false);
	//
	//		int statusCode = response.statusCode();
	//		System.out.println("Status Code: "+statusCode);
	//		Assert.assertEquals(statusCode, 204);
	//
	//		Headers allheaders = response.headers();
	//		for (Header header : allheaders) {
	//			System.out.println(header.getName()+"  :  "+header.getValue());
	//		}
	//
	//		//		String successCode = response.jsonPath().get("SuccessCode");
	//		//		Assert.assertEquals(successCode, "token");
	//
	//		//		String statusLine = response.getStatusLine();
	//		//		System.out.println("Status Code  is:  "+statusLine);
	//		//		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	//	}
	
	static String BASE_URL = ConfigManager.getInstance().getString("base_url");

	public static void getResponse() {

		RestAssured.baseURI=BASE_URL;

		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.request(Method.GET,"/employees");

		System.out.println("======Body======");
		String responseBody = response.getBody().asString();
		System.out.println("Respose Body is: " +responseBody);
		Assert.assertEquals(responseBody.contains("success"), true);

		int statusCode = response.statusCode();
		System.out.println("Status Code: "+statusCode);
		Assert.assertEquals(statusCode, 200);
		
		System.out.println("*******Headers*******");
		Headers allheaders = response.headers();
		for (Header header : allheaders) {
			System.out.println(header.getName()+"  :  "+header.getValue());
		}

				String statusLine = response.getStatusLine();
				System.out.println("Status Code  is:  "+statusLine);
				Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");

		//		Response res = RestAssured.get("http://dummy.restapiexample.com/api/v1/employees");
		//		System.out.println("Response : "+res);
		//		System.out.println("Response Body: "+res.asString());
		//		System.out.println("Response Body: "+res.getBody().asString());
		//		System.out.println("Status code: "+res.getStatusCode());
		//		System.out.println("Status: "+res.getStatusLine());
		//		System.out.println("Header: "+res.getHeader("content-type"));
		//		System.out.println("RunTime: "+res.getTime());
	}

		public static void PutResponse() {
			RestAssured.baseURI=BASE_URL;
			RequestSpecification httpRequest = RestAssured.given();
			JSONObject requestParam = new JSONObject();
	
			requestParam.put("name", "dk02");
			requestParam.put("salary", "665666");
			requestParam.put("age", "28");
	
			httpRequest.header("Content-Type", "application/json");
	
			httpRequest.body(requestParam.toJSONString());
	
			Response response = httpRequest.request(Method.POST,"/create");
	
			String responseBody = response.getBody().toString();
			System.out.println("Respose Body is: " +responseBody);
			
			System.out.println("Response Body: "+response.getBody().asString());
			Assert.assertEquals(responseBody.contains("success"), false);
			
			int statusCode = response.statusCode();
			System.out.println("Status Code: "+statusCode);
			Assert.assertEquals(statusCode, 200);
	
			Headers allheaders = response.headers();
			for (Header header : allheaders) {
				System.out.println(header.getName()+"  :  "+header.getValue());
			}
			
			//		String successCode = response.jsonPath().get("SuccessCode");
			//		Assert.assertEquals(successCode, "token");
	
			//		String statusLine = response.getStatusLine();
			//		System.out.println("Status Code  is:  "+statusLine);
			//		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
		}
		
		
		public static void TryResponse() {
			RestAssured.baseURI="http://qa-igt-ttconnect.ttaws.com";
//			RequestSpecification httpRequest = RestAssured.given();
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
					.body(rBody).when().redirects().follow(true).redirects().max(100).post("/services/soap/ota/2008a/HotelService/clid/availpro");


//			int statusCode = response.statusCode();
//			System.out.println("Status Code: "+statusCode);
//			Assert.assertEquals(statusCode, 201);
//			System.out.println("Header: "+response.getHeader("content-type"));
//			System.out.println("returned full html /n" + response.getBody().asString());
			
			String responseBody = response.getBody().toString();
			System.out.println("Respose Body is: " +responseBody);
			
			System.out.println("Response Body: "+response.getBody().asString());
			Assert.assertEquals(responseBody.contains("success"), false);
			
			int statusCode = response.statusCode();
			System.out.println("Status Code: "+statusCode);
			Assert.assertEquals(statusCode, 200);
	
			Headers allheaders = response.headers();
			for (Header header : allheaders) {
				System.out.println(header.getName()+"  :  "+header.getValue());
			}
		}

}
