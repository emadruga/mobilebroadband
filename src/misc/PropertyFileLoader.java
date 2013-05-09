package misc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class PropertyFileLoader {

	
	
	
	
	
	
	public static FileInputStream loadPropertyFile(String settingsPropertiesFolder, String settingsPropertyFileName) {
		FileInputStream input = null;
		try {
			input = new FileInputStream(settingsPropertiesFolder + "/"
					+ settingsPropertyFileName);
		} catch (FileNotFoundException e) {
			System.out.println("File '"+settingsPropertyFileName+"' nao encontrado");
			System.exit(0);
		}
		return input;
	}
}
