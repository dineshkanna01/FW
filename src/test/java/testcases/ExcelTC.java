package testcases;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import Utility.ExcelUtils;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ExcelTC {

	public static void main(String[] args) throws InvalidFormatException, IOException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https:\\www.flipkart.com");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//System.out.println(Utility.getTestData(2, 1));
		driver.findElement(By.xpath("//button[@class='_2KpZ6l _2doB4z']")).click();
		driver.findElement(By.xpath("//a[text()='Login']")).click();
		driver.findElement(By.xpath("(//input[@type='text'])[2]")).sendKeys(ExcelUtils.getTestData(1,1, "Details"));
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys(ExcelUtils.getTestData(2,1, "Details"));
		driver.findElement(By.xpath("(//button[@type='submit'])[2]")).click();
		}
}
