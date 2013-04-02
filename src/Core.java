
import java.io.IOException;
import java.util.Vector;

import aux.Report;
import dataModel.Sample;
import dataModel.Valley;
import dataModel.ValleyTableModel;


public class Core {

	/**
	 * @param args
	 * @throws IOException
	 */
	

	public static void checkPossibilitiesOnDatabase(){
		
	}


	public static void putValleysOnPentaho_needsAbetterName(String startTime, String endTime, int lenght, float Delta1, float Delta2) throws IOException {

		

		//acquiring samples
		Vector<Sample> vectorOfSample = SampleGenerator.buscarAmostras(startTime, endTime, "", "");
		
		//calculating valleys
		Vector<Valley> vectorOfValley = ValleyGenerator.findValleysOnSamples(vectorOfSample, lenght, Delta1, Delta2);

		//populating a table model
		ValleyTableModel tableModel= new ValleyTableModel(vectorOfValley);
		
		//creating a new Report Object
		Report report = new Report();
		
		//showing 
		report.showChart(tableModel, "data/Lista_de_Vales2.prpt");
				
	}
	
	/*
	 * Other methods to generate different reports
	 */
}
