package extendreport;

import org.testng.Assert;
import org.testng.annotations.Test;

import newextend.TestReport;


public class ExtendTC extends TestReport{

	@Test
	public void openReservation(){
		extentTest = extent.startTest("Google1");
		String title = getDriver().getTitle();
		System.out.println(title);
		Assert.assertEquals(title,"Make a reservation12 on qatest1");
	}
}
