package helper;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class JsonHelper {

	public Map<String,String> readJsonFile(String methodName,String jsonFileName) throws JsonIOException, JsonSyntaxException, FileNotFoundException{
		//String filepath=System.getProperty("user.dir")+File.separator+"resources"+File.separator+"userManagement"+File.separator+jsonFileName;
		String filepath = System.getProperty("user.dir")+"\\src\\test\\resources\\UserManagement\\UserManagementTest.json";
		JsonElement jsonElement=(new JsonParser()).parse(new FileReader(filepath));
		JsonObject object=jsonElement.getAsJsonObject();
		JsonElement ele=object.get(methodName);
		JsonObject jObject=ele.getAsJsonObject();
		Map<String,String> mobject=new HashMap<String, String>();

		Iterator iterator=jObject.entrySet().iterator();

		while(iterator.hasNext()) {
			Entry entry=(Entry) iterator.next();

			mobject.put(entry.getKey().toString(), entry.getValue().toString());

		}

		return mobject;

	}
}
