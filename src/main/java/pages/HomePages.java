package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.TestBase;

public class HomePages extends TestBase{

	public HomePages() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="(//span[text()=\"Industries\"])[1]")
	WebElement txtIndustries;
	
	@FindBy(xpath="//span[text()=\"Gaming\"]")
	WebElement txtGames;
	
	@FindBy(xpath="//span[text()=\"Quick Connect\"]")
	WebElement txtQuick;
	
	@FindBy(xpath="//img[contains(@alt,'IGT Solutions')]")
	WebElement imgLogo;
	
	public String UrlTilte() {
		return driver.getTitle();
	}
	
	public boolean Logo() {
		return imgLogo.isDisplayed();
	}
	
	
	public HomePages txtHomepages() {
		txtIndustries.click();
		txtGames.click();
		return null;
	}
	
	public void txtConnect() {
		imgLogo.click();
	}
}
