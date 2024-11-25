package configs;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import tests.BaseTest;

public class PropertiesFile {
	//Create a object for class properties 
	static Properties prop = new Properties();
	static String projectPath = System.getProperty("user.dir");

	public static void main(String[] args) {
		getProperties();
	}

	public static void getProperties() {
		try {
			//create a object for class InputStream
			InputStream input = new FileInputStream(projectPath + "/src/test/java/configs/Configuration.properties");
			// Load properties file
			prop.load(input);
			// get values from properties file
			BaseTest.url = prop.getProperty("URL");
		} catch (Exception exp) {
			System.out.println(exp.getMessage());
			System.out.println(exp.getCause());
			exp.printStackTrace();
		}
	}

}
