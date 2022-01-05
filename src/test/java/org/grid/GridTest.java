package org.grid;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class GridTest {
	
	public static WebDriver driver;
	public static Platform WIN10;
	public static String nodeURL;
	
	public static void setUp(String portNo) throws Exception {
		
		if (portNo.equalsIgnoreCase("4455")) {
			nodeURL = "http://192.168.1.4:4455/wd/hub";
			DesiredCapabilities capabilities= new DesiredCapabilities();
			capabilities.setBrowserName("chrome");
			capabilities.setPlatform(Platform.WIN10);
			
			ChromeOptions options= new ChromeOptions();
			options.merge(capabilities);
			
			WebDriver driver=new RemoteWebDriver(new URL(nodeURL),options);
			
			driver.get("https://www.igtsolutions.com/");
			System.out.println("Title: "+driver.getTitle());
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.close();
			
		}else if(portNo.equalsIgnoreCase("4456")) {
			nodeURL = "http://192.168.1.4:4456/wd/hub";

			DesiredCapabilities capabilities= new DesiredCapabilities();
			capabilities.setBrowserName("firefox");
			capabilities.setPlatform(Platform.WIN10);

			FirefoxOptions options2= new FirefoxOptions();
			options2.merge(capabilities);

			WebDriver driver=new RemoteWebDriver(new URL(nodeURL),options2);
			
			driver.get("https://www.igtsolutions.com/");
			System.out.println("Title: "+driver.getTitle());
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.close();
		}
	}
}
