package dataModel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Vector;

public class CdfOrganizer {

	public String rFparameter = null;
	public String operatorName = null;
	public Vector<Integer> intervals = new Vector<Integer>();
	public Vector<Integer> frequencies = new Vector<Integer>();
	public Vector<Double> relativeFrequencies = new Vector<Double>();
	public Vector<Double> cumulativeRelativeFrequencies = new Vector<Double>();

	private int lowerLimit = 0, upperLimit = 0, classInterval = 0; // Put on
																	// admin.properties
	int numberOfClasses; // Don't put on admin.properties
	
	private static final String settingsPropertiesFolder = "config";
	private static final String settingsPropertyFileName = "settings.properties";
	
	private FileInputStream loadPropertyFile() {
		FileInputStream input = null;
		try {
			input = new FileInputStream(settingsPropertiesFolder + "/"
					+ settingsPropertyFileName);
		} catch (FileNotFoundException e) {
			System.out.println("File '"+settingsPropertyFileName+"' nao encontrado");
			System.exit(0);
		}
		return input;
	}
	
	private void loadParameters() {
		Properties props = new Properties();
		try {
			props.load(loadPropertyFile());
			this.lowerLimit=Integer.parseInt(props.getProperty(this.rFparameter+"lowerLimit"));
			this.upperLimit=Integer.parseInt(props.getProperty(this.rFparameter+"upperLimit"));
			this.classInterval=Integer.parseInt(props.getProperty(this.rFparameter+"classInterval"));
			//numberOfClasses = Math.abs((Math.abs(upperLimit-lowerLimit)) / classInterval);
			if (Math.abs(this.upperLimit) > Math.abs(this.lowerLimit))
			this.numberOfClasses=(Math.abs(this.upperLimit) - Math.abs(this.lowerLimit))/this.classInterval;
			else
				this.numberOfClasses=(Math.abs(this.lowerLimit) - Math.abs(this.upperLimit))/this.classInterval;
				
				
			
		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(lowerLimit);
		System.out.println(upperLimit);
		System.out.println(classInterval
				);
	}
	
	
	
	/*
	 * constructor
	 */
	public CdfOrganizer(Vector<Sample> vectorOfSamples, String parameter,
			String operatorName) {
		// set some variables
		this.rFparameter = parameter;
		this.operatorName = operatorName;
		loadParameters();
		
		generateData(retrieveParameteres(vectorOfSamples, this.rFparameter,
				this.operatorName));
		printOut();
	}

	// Creates a simple vector of values to be used
	private Vector<Double> retrieveParameteres(Vector<Sample> vectorOfSamples,
			String parameter, String operator) {
		Vector<Double> vectorOfParameters = new Vector<Double>();

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
	
		/*
		 * Calculates the acu
		 */
		cumulativeRelativeFrequencies.add(relativeFrequencies.get(0));

		double acuFrequencias = 0;
		for (int i = 0; i < relativeFrequencies.size(); i++) {
			acuFrequencias = acuFrequencias + relativeFrequencies.get(i);
			cumulativeRelativeFrequencies.add(acuFrequencias);
		}
	}

	/*
	 * Just for debug
	 */
	private void printOut() {
		System.out.println("Classes: " + numberOfClasses);
		
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