import java.util.Vector;
import java.util.Iterator;
import java.util.Date;

import javax.swing.table.DefaultTableModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

public class ExampleTableModel extends DefaultTableModel {
    private final Float DELTA_1 = 50.0F;
    private final Float DELTA_2 = 50.0F;
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private Vector<Vector<Object>> vData = null;
    private Vector<Vector<Object>> valleys = null;

    Object columnNames[] = new Object[] {"ValleyID", "SampleTime", "Speed"};

    Object data[][] = new Object[][] {
		{1,"2012-10-26 10:56:38", 400.56F },
		{1,"2012-10-26 10:56:39", 300.56F },
		{1,"2012-10-26 10:56:46",1302.56F },
		{1,"2012-10-26 10:56:47",1102.56F },
		{1,"2012-10-26 10:56:48",1002.56F },
		{1,"2012-10-26 10:56:49", 502.56F },
		{1,"2012-10-26 10:56:50", 802.56F },
		{1,"2012-10-26 10:56:51", 902.56F },
		{1,"2012-10-26 10:56:52",1402.56F },
		{1,"2012-10-26 10:56:53", 300.56F },
		{1,"2012-10-26 10:56:54", 400.56F },
		{1,"2012-10-26 10:56:55", 400.56F },
		{1,"2012-10-26 10:56:56", 300.56F },
		{2,"2012-10-26 10:56:57",1301.56F },
		{2,"2012-10-26 10:56:58",1101.56F },
		{2,"2012-10-26 10:56:59",1001.56F },
		{2,"2012-10-26 10:57:01", 501.56F },
		{2,"2012-10-26 10:57:02", 801.56F },
		{2,"2012-10-26 10:57:03", 901.56F },
		{2,"2012-10-26 10:57:04",1401.56F },
		{1,"2012-10-26 10:57:05", 300.56F },
		{1,"2012-10-26 10:57:06", 400.56F },
		{3,"2012-10-26 10:57:07",1303.56F },
		{3,"2012-10-26 10:57:08", 503.56F },
		{3,"2012-10-26 10:57:09", 803.56F },
		{3,"2012-10-26 10:57:10", 903.56F },
		{3,"2012-10-26 10:57:11",1403.56F },
		{1,"2012-10-26 10:57:12", 400.56F },
		{1,"2012-10-26 10:57:13", 300.56F },
		{1,"2012-10-26 10:57:14",1300.56F }
	};


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
	    Date   tmstamp = resultSet.getDate("ResultsFTPTest.msgTime");
	    String session = resultSet.getString("Sessions.SessionID");
	    String tput    = resultSet.getString("throughput");
	    System.out.println("Time: "    + tmstamp);
	    System.out.println("Session: " + session);
	    System.out.println("Throughput (Kbps): " + tput);
	}
    }

    // You need to close the resultSet
    private void close() {
	try {
	    if (resultSet != null) {
		resultSet.close();
	    }

	    if (statement != null) {
		statement.close();
	    }

	    if (connect != null) {
		connect.close();
	    }
	} catch (Exception e) {

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
	    //writeMetaData(resultSet);
	    vData = generateData(resultSet);
	    //writeResultSet(resultSet);
      
	} catch (Exception e) {
	    throw e;
	} finally {
	    close();
	}

    }

    public Vector<Vector<Object>> generateData() {
	Vector<Vector<Object>> v = new Vector<Vector<Object>>();
	for (int i = 0; i < data.length; i++) {
	    Vector<Object> r = new Vector<Object>();
	    Integer     session = (Integer) data[i][0];
	    Timestamp   tmstamp = Timestamp.valueOf((String) data[i][1]);
	    Float       tput    = (Float) data[i][2];
	    r.add(session);
	    r.add(tmstamp);
	    r.add(tput);
	    v.add(r);
	}
	return v;
    }

    private Vector<Vector<Object>> generateData(ResultSet resultSet) throws SQLException {
		Vector<Vector<Object>> v = new Vector<Vector<Object>>();
		while (resultSet.next()) {
			Vector<Object> r = new Vector<Object>();
			Timestamp   tmstamp = resultSet.getTimestamp("ResultsFTPTest.msgTime");
			Integer     session = resultSet.getInt("Sessions.SessionID");
			Float       tput    = resultSet.getFloat("throughput");
			r.add(session);
			r.add(tmstamp);
			r.add(tput);
			v.add(r);
		}
		return v;
    }

    private boolean goingDown(Vector<Vector<Object>> original, int s, int v) {
	System.out.print("GoingDown: ");
	return  goingDownByDelta(original,s,v,0.0F);
    }

    private boolean goingDownByDelta(Vector<Vector<Object>> original, int s, int v, Float delta) {
	boolean response = false;
	Vector<Object> sampleS = original.get(s);
	Vector<Object> sampleV = original.get(v);
	Float       tputS    = (Float) sampleS.get(2);
	Float       tputV    = (Float) sampleV.get(2);

	Float diff = ((tputS - tputV)/tputS) * 100.0F;

	if (tputS > tputV && diff >  delta ) {
	    response = true;
	}
	System.out.println("GoingDownByDelta: s = " + tputS  + " v = " + tputV + " (" + response + ", " + diff +")");

	return response;
    }

    private boolean goingUp(Vector<Vector<Object>> original, int v, int e) {
	System.out.print("GoingUp: ");
	return goingUpByDelta(original,v,e,0.0F);
    }

    private boolean goingUpByDelta(Vector<Vector<Object>> original, int v, int e, Float delta) {

	boolean response = false;
	Vector<Object> sampleV = original.get(v);
	Vector<Object> sampleE = original.get(e);
	Float       tputV    = (Float) sampleV.get(2);
	Float       tputE    = (Float) sampleE.get(2);

	Float diff = ((tputE - tputV)/tputE) * 100.0F;


	if ((tputE > tputV) && (diff >  delta)) {
	    response = true;
	}

	System.out.println("GoingUpByDelta: v = " + tputV  + " e = " + tputE + " (" + response + ", " + diff + ", " + delta + ")");

	return response;
    }

    private void appendValley(Vector<Vector<Object>> original, Vector<Vector<Object>> resp, Integer id, int s, int v, int e) {
	if (resp == null) {
	    return;
	}
	for (int i = s; i <= e; i++) {
	    Vector<Object> sample = original.get(i);
	    Integer     session = (Integer) sample.get(0);
	    Timestamp   tmstamp = (Timestamp) sample.get(1);
	    Float       tput    = (Float) sample.get(2);
	
	    Vector<Object> valleySample = new Vector<Object>();
	    valleySample.add(id);
	    //valleySample.add(session);
	    valleySample.add(tmstamp);
	    valleySample.add(tput);
	    System.out.println("Append: s = " + tput );
	    resp.add(valleySample);
	}
    }

    private int interestingWayDown( Vector<Vector<Object>> original, int start, Float delta) {
	int foundValley = 0;
	int valley = start+1;
	while(  valley < original.size() && (valley - start) <= 3 ) {
	    if(goingDownByDelta(original, start, valley, delta)) {
		// we found a possible valley... 
		foundValley = valley;
		valley++;
	    } else if (goingDown(original, start, valley)) {
		valley++;
		continue;
	    } else {
		// Not going down in this step.
		// Start all over from the next possible start
		break;
	    }
	}
	return foundValley;
    }
    
    private int interestingWayUp( Vector<Vector<Object>> original, int valley, Float delta) {

	int foundEnd = 0;
	int end = valley+1;
	Float max = 0.0F;

	while(end < original.size() && ( end - valley) <= 3) {
	    if (goingUpByDelta(original, valley, end, delta)) {
		Vector<Object> sample  = original.get(end);
		Float          tput    = (Float) sample.get(2);
		if (tput > max) {
		    max = tput;
		    foundEnd = end;
		}
		end++;
	    } else if (goingUp(original, valley, end)) {
		end++;
		continue;
	    } else {
		// Not going up in this step.
		// Start all over from a the next possible start
		break;
	    }
	}
	return foundEnd;
    }

    private Vector<Vector<Object>> filter( Vector<Vector<Object>> original) {
	int start = 0;
	int  valley = 0;
	int  end = 0;
	Integer count = 0;
	Vector<Vector<Object>> response = null; 
	while (start < original.size() ) {
	    valley = interestingWayDown(original, start, DELTA_1);
	    if (valley > 0) {
		end = interestingWayUp(original,valley, DELTA_2);
		if (end > 0) {
		    // There is at least one "interesting" valley to keep record of
		    count++;
		    if (response == null) {
			response = new Vector<Vector<Object>>();
		    }
		    appendValley(original, response, count, start, valley, end);
		} 
	    }
	    if (end > 0) {
		start = end + 1;
		valley = 0;
		end = 0;
	    } else {
		start++;
	    }
	}
	return response;
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
		//vData = generateData();
		valleys = filter(vData);
		setDataVector(valleys, getColumnNames());
    }
	
}
