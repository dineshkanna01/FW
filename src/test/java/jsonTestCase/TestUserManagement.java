package jsonTestCase;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import helper.JsonHelper;

public class TestUserManagement {

	public static void main(String[] args) throws JsonIOException, JsonSyntaxException, FileNotFoundException {
		JsonHelper helper=new JsonHelper();
		Map<String,String> map=new HashMap<String,String>();
		
		map=helper.readJsonFile("addUser","UserManagementTest.json");
		System.out.println(map);
		System.out.println(map.get("userName"));
		System.out.println(map.get("AadharNumber"));
		System.out.println(map.get("DateofBirth"));
		
		map=helper.readJsonFile("addUser1","UserManagementTest.json");
		System.out.println(map);
		System.out.println(map.get("userName"));
		System.out.println(map.get("AadharNumber"));
		System.out.println(map.get("DateofBirth"));
		
	}
}
