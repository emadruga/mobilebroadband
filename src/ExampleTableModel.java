import java.util.Vector;
import java.util.Date;

import javax.swing.table.DefaultTableModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class ExampleTableModel extends DefaultTableModel {
    private final Float   DELTA_1       = 50.0F;
    private final Float   DELTA_2       = 50.0F;
    private final Integer MAX_TIME_SECS = 20;    // Max time interval for a valley (in secs)

    private Vector<Vector<Object>> valleys = null;

    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    Object columnNames[] = new Object[] {"ValleyID", "SampleTime", "Speed", "Session", "CQI", "MNC"};

    private void writeMetaData(ResultSet resultSet) throws SQLException {
	//   Now get some metadata from the database
	// Result set get the result of the SQL query

    	System.out.println("The columns in the table are: ");

    	System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
    	for  (int i = 1; i<= resultSet.getMetaData().getColumnCount(); i++){
    		System.out.println("Column " +i  + " "+ resultSet.getMetaData().getColumnName(i));
    	}
    }

    private void writeResultSet(ResultSet resultSet) throws SQLException {
	// ResultSet is initially before the first data set
	while (resultSet.next()) {
	    // It is possible to get the columns via name
	    // also possible to get the columns via the column number
	    // which starts at 1
	    // e.g. resultSet.getSTring(2);
	    Date   tmstamp = resultSet.getDate("msgtime");
	    String session = resultSet.getString("sessionid");
	    String tput    = resultSet.getString("throughput");
	    System.out.println("Time: "    + tmstamp);
	    System.out.println("Session: " + session);
	    System.out.println("Throughput (Kbps): " + tput);
	}
    }

    // You need to close the resultSet
    private void close() {
	try 
	    {
		if (resultSet != null) {
		    resultSet.close();
		}
		if (statement != null) {
		    statement.close();
			}
		if (connect != null) {
		    connect.close();
		}
		
	    } 
	catch (Exception e) 
	    {
		
	    }
    }

    private void readDataBase() throws Exception {

    	try {
    		// This will load the MySQL driver, each DB has its own driver
    		Class.forName("com.mysql.jdbc.Driver");
    		// Setup the connection with the DB
    		connect = DriverManager.getConnection("jdbc:mysql://localhost/arquivos_de_qualipoc?"
    				+ "user=root&password=xpto");

    		// Statements allow to issue SQL queries to the database
    		statement = connect.createStatement();

    		String myQuery = Queries.getQuery("query1");

    		// Result set get the result of the SQL quer
    		resultSet = statement.executeQuery(myQuery);

    	} catch (Exception e) {
    		throw e;
    	}
    }

    public Vector<Object> getColumnNames() {
		Vector<Object> names = new Vector<Object>();
		for (int i = 0; i < columnNames.length; i++) {
			names.add(columnNames[i]);
		}
		return names;
    }
	 
    public ExampleTableModel() throws Exception {
		super();

		readDataBase();

		SampleFilter sf = new SampleFilter(); 		

		if (resultSet != null) {
		    try 
			{
			    valleys = sf.filter(resultSet);
			    setDataVector(valleys, getColumnNames());
			} 
		    catch (Exception e) 
			{
			    throw e;
			}
		    finally
			{
			    close();
			}
		}
    }	
}
