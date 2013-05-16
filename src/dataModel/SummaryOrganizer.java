package dataModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Vector;

public class SummaryOrganizer {

	public DateFormat							df	= new SimpleDateFormat("dd/MM/yyyy");
	// public DecimalFormat def = new DecimalFormat("00.00");
	public int										all;
	public int										cqi;
	public int										ecio;
	public int										rscp;
	public int										none;
	public int										combOfTwo;
	public int										totalValleys;
	public String									operatorName;

	public float									pall;
	public float									pcqi;
	public float									pecio;
	public float									prscp;
	public float									pnone;
	public float									pcombOfTwo;

	public Vector<Vector<Object>>	finalVector;
	private String								startDate;
	private String								endDate;

	public SummaryOrganizer(Vector<Valley> vValleys, String operatorName) {

		this.startDate = df.format(vValleys.firstElement().getFirstSample().getMsgTime());
		this.endDate = df.format(vValleys.lastElement().getFirstSample().getMsgTime());

		for (Valley valley : vValleys) {
			if (valley.getFirstSample().getOperatorName() == operatorName) {
				totalValleys++;
				this.operatorName = valley.getFirstSample().getOperatorName();
				boolean ecioB = valley.testForEcioProblem();
				boolean rscpB = valley.testForRscpProblem();
				boolean cqiB = valley.testForCqiProblem();

				if (ecioB && rscpB && cqiB)
					all++;
				else
					if (!ecioB && !rscpB && !cqiB)
						none++;
					else
						if (!ecioB && !rscpB && cqiB)
							cqi++;
						else
							if (!ecioB && rscpB && !cqiB)
								rscp++;
							else
								if (ecioB && !rscpB && !cqiB)
									ecio++;
								else
									if (!ecioB && rscpB && cqiB)
										combOfTwo++;
									else
										if (ecioB && !rscpB && cqiB)
											combOfTwo++;
										else
											if (ecioB && rscpB && !cqiB)
												combOfTwo++;

			}

		}

		pall = ((float) all / totalValleys) * 100f;
		pcqi = ((float) cqi / totalValleys) * 100f;
		pecio = ((float) ecio / totalValleys) * 100f;
		prscp = ((float) rscp / totalValleys) * 100f;
		pnone = ((float) none / totalValleys) * 100f;
		pcombOfTwo = ((float) combOfTwo / totalValleys) * 100f;

		this.finalVector = new Vector<Vector<Object>>();
		Vector<Object> vector = new Vector<Object>();
		vector.add(operatorName);
		vector.add(totalValleys);
		vector.add(all);
		vector.add(cqi);
		vector.add(ecio);
		vector.add(rscp);
		vector.add(none);
		vector.add(combOfTwo);
		vector.add(pall);
		vector.add(pcqi);
		vector.add(pecio);
		vector.add(prscp);
		vector.add(pnone);
		vector.add(pcombOfTwo);
		vector.add(startDate);
		vector.add(endDate);
		this.finalVector.add(vector);

		// printOut();

	}

	private void printOut() {

		for (int i = 0; i < finalVector.size(); i++) {
			for (int j = 0; j < finalVector.get(i).size(); j++) {
				System.out.print(finalVector.get(i).get(j) + "   ");
			}
			System.out.println();
		}
	}
}
