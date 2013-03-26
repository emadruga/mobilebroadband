
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

public class Queries {

    private static final String propFileName = "queries.properties";
    private static Properties props;

    public static Properties getQueries() throws SQLException {
    	InputStream is = 
    		Queries.class.getResourceAsStream("./config/" + propFileName);
    	if (is == null){
    		throw new SQLException("Unable to open property file: " + propFileName);
    	}
    	//singleton
    	if(props == null){
    		props = new Properties();
    		try {
    			props.load(is);
    		} catch (IOException e) {
    			throw new SQLException("Unable to load property file: " + propFileName + "\n" + e.getMessage());
    		}			
    	}
    	return props;
    }

    private static String convertStreamToString(InputStream is) {
	Scanner s = new Scanner(is, "UTF-8").useDelimiter("\\A");
	if (s.hasNext()) 
	    return s.next(); 
	return "";
    }

    public static String getQuery(String query) throws SQLException{
	String fileName = getQueries().getProperty(query);

    	InputStream is = 
    		Queries.class.getResourceAsStream("./config/" + fileName);
    	if (is == null){
    		throw new SQLException("Unable to load SQL file: " + fileName);
    	}
    	return convertStreamToString(is);
    }
}