
import java.util.Vector;

import tableModel.CdfTableModel;
import tableModel.SummaryTableModel;
import tableModel.ValleyTableModel;
import aux.Report;
import dataModel.CdfOrganizer;
import dataModel.CdfOrganizer2;
import dataModel.Sample;
import dataModel.SummaryOrganizer;
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
		SummaryOrganizer claroSummaryData= new SummaryOrganizer(vectorOfValley, "Claro");
		SummaryOrganizer vivoSummaryData= new SummaryOrganizer(vectorOfValley, "Vivo");
		SummaryOrganizer timSummaryData= new SummaryOrganizer(vectorOfValley, "TIM");
		SummaryOrganizer oiSummaryData= new SummaryOrganizer(vectorOfValley, "Oi");
		
		SummaryTableModel summaryTableModel = new SummaryTableModel();
		
		summaryTableModel.add(claroSummaryData);
		summaryTableModel.add(vivoSummaryData);
		summaryTableModel.add(timSummaryData);
		summaryTableModel.add(oiSummaryData);
		
		//creating a new Report Object
		Report report = new Report();
		
		//showing 
		report.showChart(summaryTableModel, "reports/Summary.prpt");
				
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
		vivoData.init();
		CdfOrganizer timData = new CdfOrganizer(vectorOfSamples, parameter, "Claro");
		timData.init();
		CdfOrganizer claroData = new CdfOrganizer(vectorOfSamples, parameter, "TIM");
		claroData.init();
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

	public static void chart3D(String startTime, String endTime, int lenght, float Delta1, float Delta2, String parameter, int tput) {
		Vector<Sample> vectorOfSamples = SampleGenerator.findSamples(startTime, endTime, "", "");
		
		/*
		 * creates cdfPdfs objectData to each operators (There are 3 operators yet)
		 */
		CdfOrganizer2 claroData = new CdfOrganizer2(vectorOfSamples, parameter, "Claro", tput);
		claroData.init();
		
		CdfOrganizer2 timData = new CdfOrganizer2(vectorOfSamples, parameter, "TIM", tput);
		timData.init();
		
		CdfOrganizer2 vivoData = new CdfOrganizer2(vectorOfSamples, parameter, "Vivo", tput);
		vivoData.init();
		
		CdfTableModel tableModel = new CdfTableModel();
		
		tableModel.add(claroData);
		tableModel.add(timData);
		tableModel.add(vivoData);
		
		Report report = new Report();
		
		report.showChart(tableModel, "reports/pdfCdfMultiOperator.prpt");

	}
}
