package aux;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class QueryReader {

	public static String getQuery(String path) throws IOException {

		BufferedReader br = new BufferedReader(new FileReader(path));
		String s, query = new String();
		while ((s = br.readLine()) != null) {
			query = query + s;
		}
		br.close();
		return query;
	}
	
}
