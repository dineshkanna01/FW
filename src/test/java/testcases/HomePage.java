package testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.TestBase;
import pages.HomePages;

public class HomePage extends TestBase{
	
	public static HomePages hp;
	
	public HomePage() {
		super();
	}
	
	@BeforeMethod
	public void setup() {
		initilization();
		hp = new HomePages();
	}
	
	@Test(priority=1)
	public void title() {
		String title = hp.UrlTilte();
		System.out.println(title);
		Assert.assertEquals(title, "IGT Solutions: BPM, IT and Digital Services & Solutions Provider");
	}
	
	@Test(priority=2)
	public void imgTest() {
		boolean logo = hp.Logo();
		Assert.assertTrue(logo);
	}
	
	@Test(priority=3)
	public void dDnIndustries() {
		hp.txtHomepages();
	}
	
	@Test(priority=4)
	public void btnConnect() {
		hp.txtConnect();
	}
	
	@AfterMethod
	public void browerClose() {
		driver.quit();
	}
}
