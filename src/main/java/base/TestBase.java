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
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import Utility.TestUtils;
import cmdprompt.SyncPipe;
import helper.JsonHelper;
import io.github.bonigarcia.wdm.WebDriverManager;


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
		}else if(browserName.equals("IE")) {
			WebDriverManager.iedriver().setup();
			driver=new InternetExplorerDriver();
		}else {
			System.out.println("No browser");
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(TestUtils.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtils.IMPLICITWAIT, TimeUnit.SECONDS);

		driver.get(prop.getProperty("url"));
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
		email.setHostName("smtp-mail.outlook.com");
		email.setSmtpPort(587);
		email.setAuthenticator(new DefaultAuthenticator("username", "password"));
		email.setSSLOnConnect(true);
		email.setFrom("dinesh.kanna@igtsolutions.com");
		email.setSubject("TestMail");
		email.setMsg("This is a test mail ... :-)");
		email.addTo("dinesh.kanna@igtsolutions.com");
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
}
