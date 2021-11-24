package testcases;

import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import Utility.ExcelUtility;

public class ExcelTestCase {

	@Test
	public void test() {
		try {
			List<Map<String, String>> testDataMap = ExcelUtility.getTestData();
			System.out.println(testDataMap.get(4).get("Test Case Description"));
			System.out.println(testDataMap.get(4).get("Test Status"));
//			System.out.println(testDataMap.get(1).get(arg0));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
