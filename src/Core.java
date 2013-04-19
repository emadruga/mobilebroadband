
import java.util.Vector;

import tableModel.CdfTableModel;
import tableModel.SummaryTableModel;
import tableModel.ValleyTableModel;
import aux.Report;
import dataModel.CdfOrganizer;
import dataModel.Sample;
import dataModel.Valley;

public class Core {

	
	/**
	 * Launches a report that puts side by side RF parameters with FTP informations
	 * @param startTime
	 * @param endTime
	 * @param lenght
	 * @param Delta1
	 * @param Delta2
	 */
	
	public static void launchChartFTP(String startTime, String endTime, int lenght, float Delta1, float Delta2) {

		//acquiring samples
		Vector<Sample> vectorOfSamples = SampleGenerator.findSamples(startTime, endTime, "", "");
		
		//calculating valleys
		Vector<Valley> vectorOfValley = ValleyGenerator.findValleysOnSamples(vectorOfSamples, lenght, Delta1, Delta2);

		//populating a table model
		ValleyTableModel tableModel= new ValleyTableModel(vectorOfValley);
		
		//creating a new Report Object
		Report report = new Report();
		
		//showing 
		report.showChart(tableModel, "reports/Lista_de_Vales2.prpt");
				
	}
	
	/**
	 * Launches a summary report
	 * @param startTime
	 * @param endTime
	 * @param lenght
	 * @param Delta1
	 * @param Delta2
	 */

	
	public static void summary(String startTime, String endTime, int lenght, float Delta1, float Delta2){

		//acquiring samples
		Vector<Sample> vectorOfSamples = SampleGenerator.findSamples(startTime, endTime, "", "");
		
		//Vector<Sample> vectorOfSamples = SampleGenerator.findSamples(startTime, endTime, "", "");
		 
		//calculating valleys
		Vector<Valley> vectorOfValley = ValleyGenerator.findValleysOnSamples(vectorOfSamples, lenght, Delta1, Delta2);

		//populating a table model
		
		SummaryTableModel tableModel= new SummaryTableModel(vectorOfValley);
		
		//creating a new Report Object
		Report report = new Report();
		
		//showing 
		report.showChart(tableModel, "reports/Summary.prpt");
				
	}
	
	public static void cdfXPdf(String startTime, String endTime, int lenght, float Delta1, float Delta2, String parameter) {
		//acquiring samples
		Vector<Sample> vectorOfSamples = SampleGenerator.findSamples(startTime, endTime, "", "");
		
		/*
		 * creates cdfPdfs objectData to each operators (There are 3 operators yet)
		 */
		System.out.println(parameter);
		CdfOrganizer VivoCqicdfPdf = new CdfOrganizer(vectorOfSamples, parameter, "Vivo");
		CdfOrganizer TimCqicdfPdf = new CdfOrganizer(vectorOfSamples, parameter, "Claro");
		CdfOrganizer ClaroCqicdfPdf = new CdfOrganizer(vectorOfSamples, parameter, "TIM");
		
		/*
		 * Table model needs to be created with 1 cdfPdf object at least
		 */
		CdfTableModel tableModel = new CdfTableModel();
		
		/*
		 * after, other cdfpdfs can be added
		 */
		tableModel.add(TimCqicdfPdf);
		tableModel.add(ClaroCqicdfPdf);
		tableModel.add(VivoCqicdfPdf);

		
		Report report = new Report();
		
		report.showChart(tableModel, "reports/pdfCdfMultiOperator.prpt");
				
	}

	/*
	 * Other methods to generate different reports
	 */
}
