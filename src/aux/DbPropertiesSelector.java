package aux;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class DbPropertiesSelector {
	
	private Properties properties = new Properties();
	
	public DbPropertiesSelector(String fileName){
		
		this.properties.setProperty("mysqlUser", "DB USER");
		this.properties.setProperty("mysqlPassword", "DB PASSWORD");
		this.properties.setProperty("mysqlDatabase", "DATABASE");
		this.properties.setProperty("mysqlServer", "ADDRESS SERVER");
		
		if (new File(fileName).exists()) {
			System.out.println("dbProperties.xml found in config folder");
			readFromXML(fileName);
		} else {
			System.out
					.println("The File "+fileName+" wasn't found. "
							+ "We created a default dbProperties.xml in config folder. "
							+ "Edit it correctly and try again.");
			storeToXML(fileName);
			System.exit(0);
		}
	}
	
	private void storeToXML(String fileName) {
		try {
			FileOutputStream output = new FileOutputStream(fileName);
			properties.storeToXML(output, "DB parameters");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void readFromXML(String fileName) {
		Properties propertiesFromXml = new Properties();
		try {
			FileInputStream input = new FileInputStream(fileName);
			propertiesFromXml.loadFromXML(input);
		} catch (FileNotFoundException fnf) {
			System.out
					.println("The File "+fileName+" wasn't found. "
							+ "We created a default "+fileName+" in config folder. "
							+ "Edit it correctly and try again.");
		} catch (IOException e) {
			System.out.println("IOException");
			e.printStackTrace();
		}
		
		if (this.properties.equals(propertiesFromXml)) {
			System.out.println("The "+fileName+" must be edited. ");
			System.exit(0);
		} else {
			this.properties = propertiesFromXml;
			
		}
		
	}
	
	public String getServer() {
		return this.properties.getProperty("mysqlServer");
	}
	
	public String getDatabase() {
		return this.properties.getProperty("mysqlDatabase");
	}
	
	public String getUser() {
		return this.properties.getProperty("mysqlUser");
	}
	
	public String getPassword() {
		return this.properties.getProperty("mysqlPassword");
	}
}
