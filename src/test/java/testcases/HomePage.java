package testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.TestBase;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
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
	@Description("Verify the company Title on the HomePage...")
	@Severity(SeverityLevel.CRITICAL)
	@Epic("EP01")
	@Feature("Feature1: Title")
	@Story("Story: Title Presence")
	@Step("Verify Title Presence")
	public void title() {
		String title = hp.UrlTilte();
		System.out.println(title);
		Assert.assertEquals(title, "IGT Solutions: BPM, IT and Digital Services & Solutions Provider");
	}
	
	@Test(priority=2)
	@Description("Verify the company LOGO on the HomePage...")
	@Severity(SeverityLevel.MINOR)
	@Epic("EP01")
	@Feature("Feature2: LOGO")
	@Story("Story: LOGO Presence")
	@Step("Verify LOGO Presence")
	public void imgTest() {
		boolean logo = hp.Logo();
		Assert.assertTrue(logo);
	}
	
	@Test(priority=3)
	@Description("Verify the Details on the HomePage...")
	@Severity(SeverityLevel.NORMAL)
	@Epic("EP01")
	@Feature("Feature3: Industries Dropdown")
	@Story("Story: Industries Dropdown Presence")
	@Step("Verify Industries Dropdown Presence")
	public void dDnIndustries() {
		hp.txtHomepages();
	}
	
	@Test(priority=4)
	@Description("Verify the Details on the HomePage...")
	@Severity(SeverityLevel.CRITICAL)
	@Epic("EP01")
	@Feature("Feature4: QUICKCONNECT")
	@Story("Story: QUICKCONNECT Presence")
	@Step("Verify QUICKCONNECT Presence")
	public void btnConnect() {
		hp.txtConnect();
	}
	
	@AfterMethod
	public void browerClose() {
		driver.quit();
	}
}
