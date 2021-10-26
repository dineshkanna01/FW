package base;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import Utility.TestUtils;
import cmdprompt.SyncPipe;
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
		File file=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(file, new File("C:/Users/Dinesh.Kanna/eclipse-workspace/IGT/Screenshots/"+fileName+".jpg"));
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


}
