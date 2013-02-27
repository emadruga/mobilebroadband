import java.util.Vector;
import java.util.Iterator;
import java.util.Date;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class SampleFilter {
    private final Float   DELTA_1       = 50.0F;
    private final Float   DELTA_2       = 50.0F;
    private final Integer MAX_TIME_SECS = 20;    // Max time interval for a valley (in secs)


    private Float delta_one = 0.0F;
    private Float delta_two = 0.0F;

    private ResultSet resultSet = null;

    Object columnNames[] = new Object[] {"ValleyID", "SampleTime", "Speed", "Session", "MNC"};

    Object data[][] = new Object[][] {
	{1,"2012-10-26 10:56:38", 400.56F,600,5 },
	{1,"2012-10-26 10:56:39", 300.56F,600,5 },
	{1,"2012-10-26 10:56:46",1302.56F,600,5 },
	{1,"2012-10-26 10:56:47",1102.56F,600,5 },
	{1,"2012-10-26 10:56:48",1002.56F,600,5 },
	{1,"2012-10-26 10:56:49", 502.56F,600,5 },
	{1,"2012-10-26 10:56:50", 802.56F,600,5 },
	{1,"2012-10-26 10:56:51", 902.56F,600,5 },
	{1,"2012-10-26 10:56:52",1402.56F,600,5 },
	{1,"2012-10-26 10:56:53", 300.56F,600,5 },
	{1,"2012-10-26 10:57:14",1300.56F,600,5 }
    };


    private Vector<Vector<Object>> generateData() {
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
			Integer     session = resultSet.getInt("Sessions.SessionID");
			Timestamp   tmstamp = resultSet.getTimestamp("ResultsFTPTest.msgTime");
			Float       tput    = resultSet.getFloat("throughput");
			Integer     mnc     = resultSet.getInt("MNC");			
			r.add(session);
			r.add(tmstamp);
			r.add(tput);
			r.add(mnc);
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

	Float diff = 0.0F;
	
	if (tputS > 0.0F) {
	    diff = ((tputS - tputV)/tputS) * 100.0F;
	    if (tputS > tputV && diff >  delta ) {
		response = true;
	    }
	    System.out.println("GoingDownByDelta: s = " + tputS  + " v = " + tputV + " (" + response + ", " + diff +")");
	}

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

	Float diff = 0.0F;

	if (tputE > 0.0F) {
	    diff = ((tputE - tputV)/tputE) * 100.0F;
	    if ((tputE > tputV) && (diff >  delta)) {
		response = true;
	    }
	    System.out.println("GoingUpByDelta: v = " + tputV  + " e = " + tputE + " (" + response + ", " + diff + ", " + delta + ")");
	}

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
	    Integer     mnc     = (Integer) sample.get(3);
	
	    Vector<Object> valleySample = new Vector<Object>();
	    valleySample.add(id);
	    valleySample.add(tmstamp);
	    valleySample.add(tput);
	    valleySample.add(session);
	    valleySample.add(mnc);
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

    private long diff(Timestamp t1, Timestamp t2) {
	return  Math.abs(t1.getTime() - t2.getTime()) / 1000;
    }

    public Vector<Vector<Object>> filter(ResultSet rs) throws Exception {
	int     start = 0;
	int     valley = 0;
	int     end = 0;
	Integer count = 0;

	Vector<Vector<Object>> response = null; 
	Vector<Vector<Object>> original = null; 

	if (rs == null) {
	    return null;
	}

	try {
	    resultSet = rs;
	    original = generateData(resultSet);
	} catch (Exception e) {
	    throw e;
	}

	while (start < original.size() ) {
	    valley = interestingWayDown(original, start, DELTA_1);
	    if (valley > 0) {
		end = interestingWayUp(original,valley, DELTA_2);
		if (end > 0) {
		    Vector<Object> sampleS = original.get(start);
		    Vector<Object> sampleE = original.get(end);
		    Timestamp       timeS    = (Timestamp) sampleS.get(1);
		    Timestamp       timeE    = (Timestamp) sampleE.get(1);

		    if(diff(timeE, timeS) <= MAX_TIME_SECS ) {
			// There is another "interesting" valley to keep record of
			count++;
			if (response == null) {
			    response = new Vector<Vector<Object>>();
			}
			System.out.println("Diff (secs): "+ diff(timeE, timeS) + " ts: " + timeS + " te: " + timeE );
			appendValley(original, response, count, start, valley, end);
		    }
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
	
    
    public SampleFilter(Float delta1, Float delta2) throws Exception {
		delta_one = delta1;
		delta_two = delta2;
    }

    public SampleFilter() throws Exception {
		delta_one = DELTA_1;
		delta_two = DELTA_2;
    }
	
}
