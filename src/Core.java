
import java.io.IOException;
import java.sql.SQLException;
import java.util.Vector;

import aux.Report;
import dataModel.Sample;
import dataModel.SummaryTableModel;
import dataModel.Valley;
import dataModel.ValleyTableModel;

public class Core {

	public static void putValleysOnPentaho_needsAbetterName(String startTime, String endTime, int lenght, float Delta1, float Delta2) {

		//acquiring samples
		Vector<Sample> vectorOfSample = SampleGenerator.buscarAmostras(startTime, endTime, "", "");
		
		//calculating valleys
		Vector<Valley> vectorOfValley = ValleyGenerator.findValleysOnSamples(vectorOfSample, lenght, Delta1, Delta2);

		//populating a table model
		ValleyTableModel tableModel= new ValleyTableModel(vectorOfValley);
		
		//creating a new Report Object
		Report report = new Report();
		
		//showing 
		report.showChart(tableModel, "reports/Lista_de_Vales2.prpt");
				
	}

	public static void summary(String startTime, String endTime, int lenght, float Delta1, float Delta2){

		//acquiring samples
		Vector<Sample> vectorOfSample = SampleGenerator.buscarAmostras(startTime, endTime, "", "");
		
		//calculating valleys
		Vector<Valley> vectorOfValley = ValleyGenerator.findValleysOnSamples(vectorOfSample, lenght, Delta1, Delta2);

		//populating a table model
		SummaryTableModel tableModel= new SummaryTableModel(vectorOfValley);
		
		//creating a new Report Object
		Report report = new Report();
		
		//showing 
		report.showChart(tableModel, "reports/Summary.prpt");
				
	}
	
	/*
	 * Other methods to generate different reports
	 */
}
