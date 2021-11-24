package multibrowser;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import base.TestBase;

public class MBrowser extends TestBase{

	@Parameters("browser")
	@BeforeClass
	public void beforeclass(String browser) {

		// broser is firefox
		if(browser.equalsIgnoreCase("firefox")) {
			initilizationmultibrowser("Firefox");

		}
		// browser is chrome
		else if (browser.equalsIgnoreCase("chrome")) { 
			initilizationmultibrowser("chrome");

		} 
		// browser if edge 
		else if(browser.equalsIgnoreCase("ie")) {
			initilizationmultibrowser("IE");

		}
	}


	@Test
	public void test() {
		driver.get(prop.getProperty("url"));
	}

}
