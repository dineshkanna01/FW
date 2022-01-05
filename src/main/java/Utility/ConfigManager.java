package Utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;



public class ConfigManager {

	public static ConfigManager manager;

	public static Properties prop; 

	public ConfigManager() throws Exception {
	
			prop = new Properties();
			FileInputStream ip=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\config\\Configuration.properties");
			prop.load(ip);
		
	}
	
	public static ConfigManager getInstance() {
		
		if(manager==null)																							
		{
			synchronized (ConfigManager.class) 
			{
				try
				{
					manager=new ConfigManager();
				}

				catch(IOException e)
				{
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return manager;
	}
	
	public static String getString(String key){
		return System.getProperty(key,prop.getProperty(key));
	}

}
