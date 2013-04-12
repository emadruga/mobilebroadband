package dataModel;
import java.util.Vector;




public class CdfOrganizer {
	
	public String rFparameter=null;
	public String operatorName=null;
	public Vector<Integer> intervals = new Vector<Integer>(); 
	public Vector<Integer> frequencies = new Vector<Integer>();
	public Vector<Double> relativeFrequencies= new Vector<Double>();
	public Vector<Double> cumulativeRelativeFrequencies = new Vector<Double>();
	
	final int lowerLimit=1, upperLimit=30, classInterval=1;  //Put on admin.properties
	int numberOfClasses=Math.round(upperLimit/classInterval); //Don't put on admin.properties
	
	
	
	/*
	 * constructor
	 */
	public CdfOrganizer(Vector<Sample> vectorOfSamples, String parameter, String operatorName) {
		//set some variables
		this.rFparameter=parameter;
		this.operatorName=operatorName;

		generateData(retrieveParameteres(vectorOfSamples, parameter, operatorName));
	}
	
	
	//Creates a simple vector of values to be used
	private Vector<Double> retrieveParameteres(Vector<Sample> vectorOfSamples, String parameter, String operator){
		Vector<Double>vectorOfParameters = new Vector<Double>();
		
		switch (parameter) {

		case "rscp":
			for (int i = 0; i < vectorOfSamples.size(); i++) {
				if (operator == vectorOfSamples.get(i).getOperatorName())
					vectorOfParameters.add(vectorOfSamples.get(i).getRscp());
			}
			break;

		case "cqi":
			for (int i = 0; i < vectorOfSamples.size(); i++) {
				if (operator == vectorOfSamples.get(i).getOperatorName())
					vectorOfParameters.add(vectorOfSamples.get(i).getCqi());
			}
			break;

		case "ecio":
			for (int i = 0; i < vectorOfSamples.size(); i++) {
				if (operator == vectorOfSamples.get(i).getOperatorName())
					vectorOfParameters.add(vectorOfSamples.get(i).getEcio());
			}
			break;

		default:
			System.out.println("You have to select an option.");
			System.exit(0);
			break;
		}
		return vectorOfParameters;

	}
	
	
	private void generateData(Vector<Double> vectorOfParameter) {

		System.out.println("Classes: " + numberOfClasses);

		/*
		 * prepara um vetor com os valores de referencia dos intervalos
		 */
		int acuIntervalo = lowerLimit;
		for (int i = 0; i < numberOfClasses; i++) {
			intervals.add(acuIntervalo);
			acuIntervalo = acuIntervalo + classInterval;

		}

		/*
		 * conta as ocorrencias
		 */
		for (int i = 0; i < numberOfClasses; i++) {
			int count = 0; // contador de ocorrencias na classe
			for (int j = 0; j < vectorOfParameter.size(); j++) {
				double cqi = vectorOfParameter.get(j); // tratar isso para ser
														// universal
				if ((cqi >= intervals.get(i))
						&& (cqi < intervals.get(i) + classInterval)) {
					count++;
				}

			}
			frequencies.add(count);

			double divisao = ((double) count / (double) vectorOfParameter
					.size());
			relativeFrequencies.add(divisao * 100);
		}

		/*
		 * Calc relative frequencies
		 */
		double somaDasFrequenciasRelativas = 0;
		for (int i = 0; i < relativeFrequencies.size(); i++) {
			somaDasFrequenciasRelativas = somaDasFrequenciasRelativas
					+ relativeFrequencies.get(i);
		}
		System.out.println("soma das frequencias relativas = "
				+ somaDasFrequenciasRelativas);

		/*
		 * Calculates the acu
		 */
		cumulativeRelativeFrequencies.add(relativeFrequencies.get(0));

		double acuFrequencias = 0;
		for (int i = 0; i < relativeFrequencies.size(); i++) {
			acuFrequencias = acuFrequencias + relativeFrequencies.get(i);
			cumulativeRelativeFrequencies.add(acuFrequencias);
		}

		/*
		 * Just for debug
		 */

		System.out.println("Tamanho: " + cumulativeRelativeFrequencies.size());

		for (int i = 0; i < intervals.size(); i++) {
			System.out.print(intervals.get(i) + " a < "
					+ (intervals.get(i) + classInterval) + "    ");
			System.out.print(frequencies.get(i) + "   ");
			System.out.print(relativeFrequencies.get(i) + "  ");
			System.out.println(cumulativeRelativeFrequencies.get(i));
		}

	}

}
