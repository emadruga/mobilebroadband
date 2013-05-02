package control;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import tableModel.CdfTableModel;
import tableModel.SummaryTableModel;
import tableModel.ValleyTableModel;
import aux.MysqlConnection;
import aux.QueryConstructor;
import aux.Report;
import dataModel.CdfOrganizer;
import dataModel.CdfOrganizer2;
import dataModel.Sample;
import dataModel.SampleGenerator;
import dataModel.SummaryOrganizer;
import dataModel.Valley;
import dataModel.ValleyGenerator;

public class Core {
	
	public static TableModel updateTable(Vector<Integer>operatorsSelected) {
		
		DefaultTableModel model = new DefaultTableModel();
		MysqlConnection conection = new MysqlConnection("config/dbProperties.xml");
		
		String query = QueryConstructor.queryPrepare("updateTable", operatorsSelected, null);
		
		System.out.println(query);
		
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		try {
			conection.conectar();
			
			Statement stm = conection.getConexao().createStatement();
			ResultSet result = stm.executeQuery(query);
			
			data = new Vector<Vector<Object>>();
			Vector<Object> vector;
			
			while (result.next()) {
				vector = new Vector<Object>();
				vector.add(result.getString("campaignDate"));
				vector.add(result.getString("startTime"));
				vector.add(result.getString("endTime"));
				vector.add(result.getString("numSessions"));
				vector.add(result.getString("numSamples"));
				data.add(vector);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		Vector<Object>columnNames = new Vector<Object>();
		columnNames.add("Date");
		columnNames.add("Start Time");
		columnNames.add("End Time");
		columnNames.add("Sessions");
		columnNames.add("Samples");
		
		model.setDataVector(data, columnNames);
		
		
		
		
		
		//Vector<Sample> vectorOfSamples = SampleGenerator.findSamples(operatorsSelected);
		return model;
	}

	public static void launchChartFTP(Vector<Integer> operatorsSelected, Vector<String> campaignsList, 
			int lenght, float Delta1, float Delta2) {

		// acquiring samples
		Vector<Sample> vectorOfSamples = SampleGenerator.findSamples(operatorsSelected, campaignsList);

		// calculating valleys
		Vector<Valley> vectorOfValley = ValleyGenerator.findValleysOnSamples(
				vectorOfSamples, lenght, Delta1, Delta2);

		if (vectorOfValley != null) {
			// populating a table model
			ValleyTableModel tableModel = new ValleyTableModel(vectorOfValley);

			// creating a new Report Object
			Report report = new Report();

			// showing
			report.showChart(tableModel, "reports/Lista_de_Vales2.prpt");
		} else
			JOptionPane
					.showMessageDialog(null, "There is no valley to analyse");
	}
	
	public static void summary(Vector<Integer> operatorsSelected, Vector<String>  campaignsList, int lenght,
			float Delta1, float Delta2) {

		// acquiring samples
		Vector<Sample> vectorOfSamples = SampleGenerator
				.findSamples(operatorsSelected, campaignsList);

		// calculating valleys
		Vector<Valley> vectorOfValley = ValleyGenerator.findValleysOnSamples(
				vectorOfSamples, lenght, Delta1, Delta2);

		if (vectorOfValley != null) {
			// populating a table model
			SummaryOrganizer claroSummaryData = new SummaryOrganizer(
					vectorOfValley, "Claro");
			SummaryOrganizer vivoSummaryData = new SummaryOrganizer(
					vectorOfValley, "Vivo");
			SummaryOrganizer timSummaryData = new SummaryOrganizer(
					vectorOfValley, "TIM");
			SummaryOrganizer oiSummaryData = new SummaryOrganizer(
					vectorOfValley, "Oi");

			SummaryTableModel summaryTableModel = new SummaryTableModel();

			summaryTableModel.add(claroSummaryData);
			summaryTableModel.add(vivoSummaryData);
			summaryTableModel.add(timSummaryData);
			summaryTableModel.add(oiSummaryData);

			// creating a new Report Object
			Report report = new Report();

			// showing
			report.showChart(summaryTableModel, "reports/Summary.prpt");
		} else
			JOptionPane
					.showMessageDialog(null, "There is no valley to analyse");

	}
	
	public static void cdfXPdf(Vector<Integer> operatorsSelected, Vector<String> campaignsList, int lenght, float Delta1, float Delta2, String parameter) {
		//acquiring samples
		Vector<Sample> vectorOfSamples = SampleGenerator.findSamples(operatorsSelected, campaignsList);
		
		CdfTableModel tableModel = new CdfTableModel();
		
		
		/*
		 * creates cdfPdfs objectData to each operators (There are 3 operators yet)
		 */
		
		for (int i = 0; i < operatorsSelected.size(); i++) {
			switch (operatorsSelected.get(i)) {
			case 2: 
				CdfOrganizer timData = new CdfOrganizer(vectorOfSamples, parameter, "TIM");
				timData.init();
				tableModel.add(timData);
				break;
				
			case 5: 
				CdfOrganizer claroData = new CdfOrganizer(vectorOfSamples, parameter, "Claro");
				claroData.init();
				tableModel.add(claroData);
				break;
				
			case 11: 
				CdfOrganizer vivoData = new CdfOrganizer(vectorOfSamples, parameter, "Vivo");
				vivoData.init();
				tableModel.add(vivoData);
				break;
				
			case 31: 
				CdfOrganizer oiData = new CdfOrganizer(vectorOfSamples, parameter, "Oi");
				oiData.init();
				tableModel.add(oiData);
				break;
			default:
				break;
			}
		}
		
	
		/*
		 * instantiates a report
		 */
		Report report = new Report();
		
		/*
		 * show 
		 */
		report.showChart(tableModel, "reports/pdfCdfMultiOperator.prpt");
				
	}

	public static void chart3D(Vector<Integer> operatorsSelected, Vector<String> campaignsList, int lenght, float Delta1, float Delta2, String parameter, int tput) {
		Vector<Sample> vectorOfSamples = SampleGenerator.findSamples(operatorsSelected, campaignsList);
		
		/*
		 * creates cdfPdfs objectData to each operators (There are 3 operators yet)
		 */
		CdfTableModel tableModel = new CdfTableModel();
		for (int i = 0; i < operatorsSelected.size(); i++) {
			switch (operatorsSelected.get(i)) {
			case 2: 
				CdfOrganizer2 timData = new CdfOrganizer2(vectorOfSamples, parameter, "TIM", tput);
				timData.init();
				tableModel.add(timData);
				break;
				
			case 5: 
				CdfOrganizer2 claroData = new CdfOrganizer2(vectorOfSamples, parameter, "Claro", tput);
				claroData.init();
				tableModel.add(claroData);
				break;
				
			case 11: 
				CdfOrganizer2 vivoData = new CdfOrganizer2(vectorOfSamples, parameter, "Vivo", tput);
				vivoData.init();
				tableModel.add(vivoData);
				break;
				
			case 31: 
				CdfOrganizer2 oiData = new CdfOrganizer2(vectorOfSamples, parameter, "Oi", tput);
				oiData.init();
				tableModel.add(oiData);
				break;
			default:
				break;
			}
		}
		
		
		Report report = new Report();
		
		report.showChart(tableModel, "reports/pdfCdfMultiOperator.prpt");

	}
}
