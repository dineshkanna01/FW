package testcases;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import base.TestBase;
import io.qameta.allure.Attachment;

public abstract class Listener implements ITestListener{
	
	private static String getTestMethodName(ITestResult iTestResult) {
		return iTestResult.getMethod().getConstructorOrMethod().getName();
		
	}
	
	@Attachment
	public byte[] saveFailureScreenshot(WebDriver driver) {
		return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
	}
	
	@Attachment(value = "{0}", type="text/plain")
	public static String saveTextLog(String message) {
		return message;
	}

	
	public void onTestStart(ITestResult iTestResult) {
		System.out.println("I am in onTestStart method "+getTestMethodName(iTestResult));
	}

	public void onTestSuccess(ITestResult iTestResult) {
		System.out.println("I am in onTestSuccess method "+getTestMethodName(iTestResult) +"Success");
	}

	public void onTestFailure(ITestResult iTestResult) {
		System.out.println("I am in onTestFailure method "+getTestMethodName(iTestResult) +"Failed");
		Object testClass = iTestResult.getInstance();
		WebDriver driver=TestBase.driver;
		if (driver instanceof WebDriver) {
			System.out.println("Screenshot Captured for testcase "+getTestMethodName(iTestResult));
			saveFailureScreenshot(driver);
		}
		saveTextLog(getTestMethodName(iTestResult)+"failed and screenshot taken!");
	}

	public void onTestSkipped(ITestResult iTestResult) {
		System.out.println("I am in onTestSkipped method "+getTestMethodName(iTestResult) +"Skipped");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
		System.out.println("Test failed but it is in defined success ratio "+getTestMethodName(iTestResult) +"SuccessPercentage");
		
	}

	public void onStart(ITestContext iTestContext) {
		System.out.println("I am in onStart method "+iTestContext.getName());
		iTestContext.setAttribute("WebDriver", TestBase.driver);
	}

	public void onFinish(ITestContext iTestContext) {
		System.out.println("I am in onFinish method "+iTestContext.getName());
	}

}
