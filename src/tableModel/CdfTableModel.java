package tableModel;

import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import dataModel.CdfOrganizer;

public class CdfTableModel extends DefaultTableModel implements IFaceTableModel {

	public Vector<Vector<Object>> data = new Vector<Vector<Object>>();
	public Vector<Object> columnames = new Vector<Object>();

	public CdfTableModel() {
		super();
		//add(cdfData);
		organizeColumnNames();
		
		
	}

	private String[] columnNames_Base = {
			"operatorName",
			"parameter",
			"intervals",
			"frequencies",
			"rFrequencies",
			"crFrequencies",
			"intervalNames",
			"date",
			"numSamples",
			"tput"
			};


	public void add(CdfOrganizer cdfData) {
		Vector<Object> vector;
		for (int i = 0; i < cdfData.intervals.size(); i++) {
			vector = new Vector<Object>();
			vector.add(cdfData.operatorName);
			vector.add(cdfData.rFparameter);
			vector.add(cdfData.intervals.get(i));
			vector.add(cdfData.frequencies.get(i));
			vector.add(cdfData.relativeFrequencies.get(i));
			vector.add(cdfData.cumulativeRelativeFrequencies.get(i));
			vector.add(cdfData.intervalNames.get(i));
			vector.add(cdfData.date);
			vector.add(cdfData.countSamples);		
			
			if (cdfData.tput!=null)
				vector.add("Throughput Analysed: "+cdfData.tput+" KBps");
			
				
			this.data.add(vector);
		}
		setDataVector(data, columnames);
		for (int i = 0; i < 5; i++) {
			System.out.println();
		}
		
		printOut();

	}

	
	private void printOut() {
		for (int i = 0; i < this.columnames.size(); i++) {
			System.out.print(" "+this.columnames.get(i)+" ");
		}
		System.out.println();
		
		for (int i = 0; i < this.data.size(); i++) {
			for (int j = 0; j < this.data.get(i).size(); j++) {
				System.out.print(" "+this.data.get(i).get(j)+" ");
			}
			System.out.println();
		}
	}
	
	public void organizeColumnNames() {
		
		for (int i = 0; i < columnNames_Base.length; i++) {
			columnames.add(columnNames_Base[i]);
		}
	}
	
	


}
