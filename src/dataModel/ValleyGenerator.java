package dataModel;

import java.sql.Timestamp;
import java.util.Vector;


public class ValleyGenerator {

	private final static Integer MAX_TIME_SECS = 20;

	private static boolean goingUp(Vector<Sample> sampleList, int v, int e) {
		////System.out.print("GoingUp: ");
		return goingUpByDelta(sampleList,v,e,0.0F);
		}
		
	private static boolean goingUpByDelta(Vector<Sample> sampleList, int v, int e, float delta) {

		boolean response = false;
		Sample sampleV = sampleList.get(v);
		Sample sampleE = sampleList.get(e);
		int       tputV    = sampleV.getThroughput();
		int       tputE    = sampleE.getThroughput();

		Float diff = 0.0F;

		if (tputE > 0.0F) {
		    diff = ((tputE - tputV)/tputE) * 100.0F;
		    if ((tputE > tputV) && (diff >  delta)) {
			response = true;
		    }
		    ////System.out.println("GoingUpByDelta: v = " + tputV  + " e = " + tputE + " (" + response + ", " + diff + ", " + delta + ")");
		}

		return response;
	    }
	
		
	
	private static int interestingWayUp( Vector<Sample> sampleList, int valley, float delta) {

		int foundEnd = 0;
		int end = valley+1;
		int max = 0;

		while(end < sampleList.size() && ( end - valley) <= 3) {
		    if (goingUpByDelta(sampleList, valley, end, delta)) {
			Sample sample  = sampleList.get(end);
			int          tput    = sample.getThroughput();
			if (tput > max) {
			    max = tput;
			    foundEnd = end;
			}
			end++;
		    } else if (goingUp(sampleList, valley, end)) {
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
	
	
		
	public  static Vector<Valley> findValleysOnSamples(Vector<Sample> sampleList,
			int lenght, float Delta1, float Delta2) {

		int start = 0; //start possible valley
		int possibleValley = 0; 
		int end = 0; 
		int count = 0;
		Vector<Valley> vectorOfValley = null; 
		 
		while (start < sampleList.size() ) {
		   
			possibleValley = interestingWayDown(sampleList, start, Delta1);
		    
			if (possibleValley > 0) {
			end = interestingWayUp(sampleList,possibleValley, Delta2);
			if (end > 0) {
			    Sample sampleS = sampleList.get(start);
			    Sample sampleE = sampleList.get(end);
			    Timestamp       timeS    = (Timestamp) sampleS.getMsgTime();
			    Timestamp       timeE    = (Timestamp) sampleE.getMsgTime();

			    if(diff(timeE, timeS) <= MAX_TIME_SECS ) {
			    	
				// There is another "interesting" valley to keep record of
				count++;
				if (vectorOfValley == null) {
				    vectorOfValley = new Vector<Valley>();
				}
				//System.out.println("Diff (secs): "+ diff(timeE, timeS) + " ts: " + timeS + " te: " + timeE );
				appendValley(sampleList, vectorOfValley, count, start, possibleValley, end);
			    }
			} 
		    }
		    if (end > 0) {
			start = end + 1;
			possibleValley = 0;
			end = 0;
		    } else {
			start++;
		    }
		}
		return vectorOfValley;

		
	}
	
	private static int interestingWayDown(Vector<Sample> sampleList, int start, float delta) {
		int foundValley = 0;
		int testedSampleIndex = start+1;
		while(testedSampleIndex < sampleList.size() && (testedSampleIndex - start) <= 3 ) {
			if(goingDownByDelta(sampleList, start, testedSampleIndex, delta)) {
				// we found a possible valley... 
				foundValley = testedSampleIndex;
				testedSampleIndex++;
			} else  if (goingDown(sampleList, start, testedSampleIndex)) {
				testedSampleIndex++;
				//continue;
			} else {
				// Not going down in this step.
				// Start all over from the next possible start
				break;
			}
		}
		return foundValley;
	}	
	
	
    private static boolean goingDownByDelta(Vector<Sample> sampleList, int s, int v, float delta) {
    	boolean response = false;
    	Sample sampleS = sampleList.get(s);
    	Sample sampleV = sampleList.get(v);
    	int       tputS    = sampleS.getThroughput();
    	int       tputV    = sampleV.getThroughput();

    	double diff = 0.0F;
    	
    	if (tputS > 0.0F) {
    	    diff = (((double) tputS - (double) tputV)/tputS);
    	    if (tputS > tputV && diff >  delta ) {
    		response = true;
    	    }
    	    //System.out.println("GoingDownByDelta: s = " + tputS  + " v = " + tputV + " (" + response + ", " + diff +")");
    	}

    	return response;
        }
    
    private static boolean goingDown(Vector<Sample> sampleList, int s, int v) {
    	//System.out.print("GoingDown: ");
    	return  goingDownByDelta(sampleList,s,v,0.0F);
        }
    
    
    private static long diff(Timestamp t1, Timestamp t2) {
    	return  Math.abs(t1.getTime() - t2.getTime()) / 1000;
        }
    
    private static void appendValley(Vector<Sample> original, Vector<Valley> resp, Integer id, int s, int v, int e) {
    	if (resp == null) {
    		return;
    	}
    	Valley oneValley = new Valley();
    	for (int i = s; i <= e; i++) {
    		
    		oneValley.addSample(original.get(i));    	
    		oneValley.setValleySample(original.get(v));
    		oneValley.setValleyId(id);
    		//System.out.println("Append: s = " + original.get(i).getMsgId());
    	}
    	resp.addElement(oneValley);
    }
}
