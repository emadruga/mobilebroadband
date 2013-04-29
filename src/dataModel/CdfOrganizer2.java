package dataModel;

import java.util.Vector;


public class CdfOrganizer2 extends CdfOrganizer {

	private Vector<Integer> vThroughputs = new Vector<Integer>();
	
	public CdfOrganizer2(Vector<Sample> vectorOfSamples, String parameter, String operatorName, int tPut) {
		super(vectorOfSamples, parameter, operatorName);
		
		this.tput=tPut;
		this.rFparameter = parameter;
		this.operatorName = operatorName;	
	}
	
	// Creates a simple vector of values to be used
	@Override
	protected void retrieveParameteres() {
		vParameter = new Vector<Double>();
		vThroughputs = new Vector<Integer>();
		switch (rFparameter) {

		case "RSCP":
			for (int i = 0; i < vectorOfSamples.size(); i++) {
				if (operatorName == vectorOfSamples.get(i).getOperatorName()) {
					this.vParameter.add(vectorOfSamples.get(i).getRscp());
					this.vThroughputs.add(vectorOfSamples.get(i).getThroughput());
				}

			}
			break;

		case "CQI":
			for (int i = 0; i < vectorOfSamples.size(); i++) {
				if (operatorName == vectorOfSamples.get(i).getOperatorName()) {
					this.vParameter.add(vectorOfSamples.get(i).getCqi());
					this.vThroughputs.add(vectorOfSamples.get(i).getThroughput());
				}
			}
			break;

		case "Ec/i0":
			for (int i = 0; i < vectorOfSamples.size(); i++) {
				if (operatorName == vectorOfSamples.get(i).getOperatorName()) {
					this.vParameter.add(vectorOfSamples.get(i).getEcio());
					this.vThroughputs.add(vectorOfSamples.get(i).getThroughput());
				}
			}
			break;

		default:
			System.out.println("You have to select an option.");
			System.exit(0);
			break;
		}

	}
	protected void countOccurrences() {
			/*
			 * conta as ocorrencias
			 */
			for (int i = 0; i < numberOfClasses; i++) {
				int count = 0; // contador de ocorrencias na classe
				for (int j = 0; j < vParameter.size(); j++) {
					double parameter = vParameter.get(j); 
					int throughput = vThroughputs.get(j);
					if ((parameter >= intervals.get(i))
							&& (parameter < intervals.get(i) + classInterval)
							&& (throughput >= this.tput)) {
						count++;
					}
				}
				frequencies.add(count);

				double divisao = ((double) count / (double) vParameter.size());
				relativeFrequencies.add(divisao * 100);
			}
		}

}