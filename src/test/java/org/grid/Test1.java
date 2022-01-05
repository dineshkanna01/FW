package org.grid;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Test1 extends GridTest{
	
	@Parameters({"portNo"})
	@BeforeMethod
	public void setup(String portNo) throws Exception {
		setUp(portNo);
	}
	
	@Test
	public void test1() {
		
	}

	@AfterMethod
	public void close() {
//		driver.quit();
	}
}
