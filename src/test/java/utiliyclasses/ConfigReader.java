package utiliyclasses;

import java.util.Properties;
import java.io.*;

public class ConfigReader {
	
	static Properties prop;
	
	public static Properties readConfigProperties(String filepath) {
		try {
			FileInputStream fis = new FileInputStream(filepath);
			prop = new Properties();
			prop.load(fis);
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		return prop;
	}
	
	public static String getPropertyValue(String key) {
		String value = null;
		value = prop.getProperty(key);
		if(value!=null) {
			return value;
		}else {
			System.out.print("key does not exist");
		}
		return value;
	}

}
