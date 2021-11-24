package extendreport;

import org.testng.Assert;
import org.testng.annotations.Test;

import extend.TestReport;

public class ExtendTC extends TestReport{

	@Test
	public void openGoogle(){
		extentTest = extent.startTest("Google");
		String title = driver.getTitle();
		System.out.println(title);
		Assert.assertEquals(title,"Google");
	}
}
