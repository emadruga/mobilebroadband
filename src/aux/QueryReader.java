package aux;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.Vector;

public class QueryReader {

	private static String queryPropertiesFolder = "config";
	private static String queryPropertyFileName = "queries.properties";

	private static FileInputStream readPropertyFile() {
		FileInputStream input = null;
		try {
			input = new FileInputStream(queryPropertiesFolder + "/"
					+ queryPropertyFileName);
		} catch (FileNotFoundException e) {
			System.out.println("Arquivo nao encontrado");
			System.exit(0);
		}
		return input;
	}

	private static String retrieveFileNameByQueryName(String queryName) {
		Properties props = new Properties();
		try {
			props.load(readPropertyFile());
		} catch (FileNotFoundException e) {
			System.out.println("File " + queryPropertyFileName + " not Found");
			System.exit(0);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return props.getProperty(queryName);
	}

	private static String retrieveQueryByFilename(String pathToFile) {
		BufferedReader br;
		String s, query = null;
		try {
			br = new BufferedReader(new FileReader(queryPropertiesFolder + "/"
					+ pathToFile));
			query = new String();
			while ((s = br.readLine()) != null) {
				query = query + s;
			}
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println("File " + pathToFile + " not Found");
			System.exit(0);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return query;

	}

	public static String retrieveQueryByName(String queryName) {
		return retrieveQueryByFilename(retrieveFileNameByQueryName(queryName));
	}

	
	public static String adjustOperator (Vector<Integer> operators) {
		String query = new String();
		for (int i = 0; i < operators.size(); i++) {
			query = query.concat(" `MNC` = "+operators.get(i));
			if (i != operators.size()-1)
				query = query.concat(" AND");
		}
		return query;
	}
	
	public static void adjustCampaign() {
		
	}
	
	
	
	
	
}
