package dataModel;

import java.sql.Timestamp;
import java.util.Vector;

public class ValleyGenerator2 {

	private final static Integer	MAX_TIME_SECS	= 20; 		//maximum valley's size in seconds
	private final static double		MIN_TPUT_START	= 300;  //minimum throughput value for first sample

	public static Vector<Valley> findValleysOnSamples(Vector<Sample> sList, int lenght, double d1, double d2) {

		int startTsd = 0; 					   // startTsd: It's a pointer to the first sample of the possible valley
		int valleyTsd = startTsd;    	 // valleyTsd: It's a pointer to "valley" sample of the possible valley 
		int endTsd = valleyTsd; 		   // endTsd: It's a pointer to the last sample of the possible valley 
		int startMAX = sList.size()-2; // startMAX: It's the last sample that could be called "startTst"
		Vector<Valley> vValley=				 // final vector to be returned by this function/procedure
				new Vector<Valley>();
		int count = 0;								 // number of valleys found in samples 

		
		//This loop moves startTsd from 0 to the antepenult sample. There is no need go beyond that point.
		while (startTsd <= startMAX) {

			//This condition stops the cycle when valleyTsd reaches the penultimate sample.
			if (valleyTsd == startMAX - 1) {
				break;
			}

			valleyTsd++;

			/*
			 * This "if" tests if the startTst and valleyTsd come from
			 * the same session and if startTsd is bigger then MIN_TPUT_START
			 */
			if ((isTheSameSession(sList, startTsd, valleyTsd)) && (isStartTstdBiggerThenValleyTstd(sList, startTsd, valleyTsd))) {

				/*
				 * This "if" tests interesting down condition. Case yes, a new loop looks for a sample to finish the valley.
				 * The amount of samples can't be bigger then "length" parameter
				 */
				if (isInterestingDescent(sList, startTsd, valleyTsd, MIN_TPUT_START, d1)) {
					endTsd = valleyTsd + 1;
					while (isSmallerThenMaxLenght(sList, startTsd, endTsd, lenght)) {

						
						//Testando aqui
						double vallasd = sList.get(valleyTsd).getThroughput();
						double endasd = sList.get(endTsd).getThroughput();
						if (vallasd>endasd) {
							valleyTsd=endTsd;
							endTsd++;
							//break;
						}
						
						
						
						if (isTheSameSession(sList, valleyTsd, endTsd)) {
							
							
						
							
							
							
							if (isInterestingRising(sList, valleyTsd, endTsd, d2)) {

								/*
								 * Here, a valley was found and have to be tested if total time is equals or minor then MAX_TIME_SECS
								 */
								if (isValleyIntoWindowTime(sList, startTsd, endTsd, MAX_TIME_SECS)) {
									count++;
									System.out.println(count + " Valley(s) Found");
									Valley valley = packagingValley(sList, startTsd, valleyTsd, endTsd, count);
									vValley.add(valley);

									startTsd = endTsd;
									valleyTsd = startTsd;
									break;

								} else {
									System.out.println("Valley Droped.");
									break;
								}

							} else {
								if (!isSmallerThenMaxLenght(sList, startTsd, endTsd + 1, lenght)) {
									startTsd = endTsd;
									valleyTsd = startTsd;
									break;
								}

								endTsd++;
							}

						} else {
							startTsd = endTsd;
							break;
						}

					}

				} else 
					if (!isSmallerThenMaxLenght(sList, startTsd, valleyTsd, lenght))
						startTsd = valleyTsd;
				

			} else
				/* if the samples aren't from the same session or startTsd is smaller then minimum,
				 * the next test will starts with startTestd in a new position
				 */
				startTsd = valleyTsd;

		}
		return vValley;
	}

	private static Valley packagingValley(Vector<Sample> sList, int startTsd, int valleyTsd, int endTsd, int count) {
		Valley valley = new Valley();

		for (int i = startTsd; i <= endTsd; i++) {
			valley.addSample(sList.get(i));
		}
		valley.setValleySample(sList.get(valleyTsd));
		valley.setValleyId(count);

		return valley;
	}

	private static boolean isValleyIntoWindowTime(Vector<Sample> sList, int startTsd, int endTsd, double maxTime) {
		boolean rsp = false;
		Timestamp timeE = (Timestamp) sList.get(endTsd).getMsgTime();
		Timestamp timeS = (Timestamp) sList.get(startTsd).getMsgTime();
		if (testDiffTime(timeE, timeS) <= MAX_TIME_SECS)
			rsp = true;

		return rsp;

	}

	private static long testDiffTime(Timestamp t1, Timestamp t2) {
		return Math.abs(t1.getTime() - t2.getTime()) / 1000;
	}

	private static boolean isStartTstdBiggerThenValleyTstd(Vector<Sample> sList, int startTsd, int valleyTsd) {
		boolean rsp = false;
		double sTput = sList.get(startTsd).getThroughput();
		double nTput = sList.get(valleyTsd).getThroughput();
		if (sTput >= nTput)
			rsp = true;
		return rsp;
	}

	private static boolean isInterestingRising(Vector<Sample> sList, int valleyTsd, int endTsd, double d2) {
		boolean rsp = false;
		double vTput = sList.get(valleyTsd).getThroughput();
		double eTput = sList.get(endTsd).getThroughput();
		double diff = ((eTput - vTput) / eTput);
		if (diff > d2)
			rsp = true;
		return rsp;
	}

	private static boolean isSmallerThenMaxLenght(Vector<Sample> sList, int value1, int value2, int lenght) {
		boolean rsp = false;
		int actRange = Math.abs(value1 - value2) + 1;
		if (actRange <= lenght)
			rsp = true;
		return rsp;
	}

	private static boolean isInterestingDescent(Vector<Sample> sList, int startTsd, int valleyTsd, double min, double d1) {
		boolean rsp = false;
		double sTput = sList.get(startTsd).getThroughput();
		double vTput = sList.get(valleyTsd).getThroughput();
		double diff = ((sTput - vTput) / sTput);
		if ((sTput >= min) && (diff > d1))
			rsp = true;
		return rsp;
	}

	private static boolean isTheSameSession(Vector<Sample> sList, int sample1, int sample2) {
		boolean rsp = false;
		int sSession = sList.get(sample1).getSessionId();
		int vSession = sList.get(sample2).getSessionId();
		if (sSession == vSession)
			rsp = true;
		return rsp;
	}
}
