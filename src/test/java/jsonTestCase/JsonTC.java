package jsonTestCase;

import org.testng.annotations.Test;

import base.TestBase;

public class JsonTC extends TestBase{
	
	@Test
	public void run() throws Exception {
		jsonFile("addUser", "UserManagementTest.json", "userName");
		jsonFile("addUser1", "UserManagementTest.json", "userName");
	}

}
