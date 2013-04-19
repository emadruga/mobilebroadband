
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
	
	/**
	 * Launches a CDF x PDF report using operator's data passed by.
	 * @param startTime
	 * @param endTime
	 * @param lenght
	 * @param Delta1
	 * @param Delta2
	 * @param parameter
	 */
	public static void cdfXPdf(String startTime, String endTime, int lenght, float Delta1, float Delta2, String parameter) {
		//acquiring samples
		Vector<Sample> vectorOfSamples = SampleGenerator.findSamples(startTime, endTime, "", "");
		
		/*
		 * creates cdfPdfs objectData to each operators (There are 3 operators yet)
		 */
		CdfOrganizer vivoData = new CdfOrganizer(vectorOfSamples, parameter, "Vivo");
		CdfOrganizer timData = new CdfOrganizer(vectorOfSamples, parameter, "Claro");
		CdfOrganizer claroData = new CdfOrganizer(vectorOfSamples, parameter, "TIM");
		
		/*
		 * creates a empty table model.
		 */
		CdfTableModel tableModel = new CdfTableModel();
		
		/*
		 * add operator's data to table model
		 */
		tableModel.add(timData);
		tableModel.add(claroData);
		tableModel.add(vivoData);

		/*
		 * instantiates a report
		 */
		Report report = new Report();
		
		/*
		 * show 
		 */
		report.showChart(tableModel, "reports/pdfCdfMultiOperator.prpt");
				
	}

}
