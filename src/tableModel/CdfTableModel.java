package tableModel;

import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import dataModel.CdfOrganizer;
import dataModel.Valley;

public class CdfTableModel extends DefaultTableModel implements IFaceTableModel {

	public Vector<Vector<Object>> data = new Vector<Vector<Object>>();
	public Vector<Object> collumnames = new Vector<Object>();

	public CdfTableModel(CdfOrganizer cdfData) {
		super();
		prepare(cdfData);
		add(cdfData);
		printOut();
	}

	private String[] collumnNames_Base = {"operatorName", "intervals",
			"frequencies", "relativeFrequencies",
			"cumulativeRelativeFrequencies", "parameter" };

	private void prepare(CdfOrganizer cdfData) {
		Vector<Object> vector;
		for (int i = 0; i < cdfData.intervals.size(); i++) {
			vector = new Vector<Object>();
			data.add(vector);
		}

	}

	public void add(CdfOrganizer cdfData) {
		
		for (int i = 0; i < cdfData.intervals.size(); i++) {
			data.get(i).add(cdfData.operatorName);
		}
		for (int i = 0; i < cdfData.intervals.size(); i++) {
			data.get(i).add(cdfData.intervals.get(i));
		}
		for (int i = 0; i < cdfData.intervals.size(); i++) {
			data.get(i).add(cdfData.frequencies.get(i));
		}
		for (int i = 0; i < cdfData.intervals.size(); i++) {
			data.get(i).add(cdfData.relativeFrequencies.get(i));
		}
		for (int i = 0; i < cdfData.intervals.size(); i++) {
			data.get(i).add(cdfData.cumulativeRelativeFrequencies.get(i));
		}
		for (int i = 0; i < cdfData.intervals.size(); i++) {
			data.get(i).add(cdfData.rFparameter);
		}
		organizeCollumnNames(cdfData.operatorName);
		setDataVector(data, collumnames);

	}

	private void organizeCollumnNames(String operatorName) {
		for (int i = 0; i < collumnNames_Base.length; i++) {
			collumnames.add(collumnNames_Base[i] + operatorName);
		}
	}

	
	private void printOut() {
		for (int i = 0; i < collumnames.size(); i++) {
			System.out.print(collumnames.get(i)+"  |  ");
		}
		System.out.println();
		for (int i = 0; i < data.size(); i++) {
			for (int j = 0; j < data.get(i).size(); j++) {
				System.out.print(data.get(i).get(j));
			}
			System.out.println();
		}
	}
	
	@Override
	public Vector<Object> organizeColumnNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector<Vector<Object>> organizeData(Vector<Valley> valleys) {
		// TODO Auto-generated method stub
		return null;
	}

}
