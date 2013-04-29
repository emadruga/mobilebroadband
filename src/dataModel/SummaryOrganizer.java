package dataModel;

import java.util.Vector;

public class SummaryOrganizer {

	public int all;
	public int cqi;
	public int ecio;
	public int rscp;
	public int cqiAecio;
	public int cqiArscp;
	public int ecioArscp;
	public int nothing;
	public int moreThenOne;
	public String operatorName;
	public Vector<Vector<Object>> finalVector;

	public SummaryOrganizer(Vector<Valley> vValleys, String operatorName) {

		for (Valley valley : vValleys) {

			if (valley.getFirstSample().getOperatorName() == operatorName) {

				this.operatorName = valley.getFirstSample().getOperatorName();

				if (valley.testForCqiProblem() && valley.testForEcioProblem()
						&& valley.testForRscpProblem())
					all++;
				if (valley.testForCqiProblem() && !valley.testForEcioProblem()
						&& !valley.testForRscpProblem())
					cqi++;
				if (!valley.testForCqiProblem() && valley.testForEcioProblem()
						&& !valley.testForRscpProblem())
					ecio++;
				if (!valley.testForCqiProblem() && !valley.testForEcioProblem()
						&& valley.testForRscpProblem())
					rscp++;
				if (valley.testForCqiProblem() && valley.testForEcioProblem()
						&& !valley.testForRscpProblem())
					cqiAecio++;
				if (valley.testForCqiProblem() && !valley.testForEcioProblem()
						&& valley.testForRscpProblem())
					cqiArscp++;
				if (!valley.testForCqiProblem() && valley.testForEcioProblem()
						&& valley.testForRscpProblem())
					ecioArscp++;
				if (!valley.testForCqiProblem() && !valley.testForEcioProblem()
						&& !valley.testForRscpProblem())
					nothing++;
			}
		}

		this.finalVector = new Vector<Vector<Object>>();
		Vector<Object> vector = new Vector<Object>();
		vector.add(all);
		vector.add(cqi);
		vector.add(ecio);
		vector.add(rscp);
		vector.add(cqiAecio);
		vector.add(cqiArscp);
		vector.add(ecioArscp);
		vector.add(nothing);
		moreThenOne = cqiAecio + cqiArscp + ecioArscp;
		vector.add(moreThenOne);
		vector.add(operatorName);
		this.finalVector.add(vector);

		//printOut();

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
