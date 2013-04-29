package tableModel;

import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import dataModel.SummaryOrganizer;

public class SummaryTableModel extends DefaultTableModel implements
		IFaceTableModel {

	private Vector<Vector<Object>> data = new Vector<Vector<Object>>();
	private Vector<Object> columnNames = new Vector<Object>();

	Object columnNamesBase[] = new Object[] { "all", "cqi", "ecio", "rscp",
			"cqiAecio", "cqiArscp", "ecioArscp", "nothing", "moreThenOne",
			"operatorName" };

	public void organizeColumnNames() {
		this.columnNames = new Vector<Object>();
		for (int i = 0; i < columnNamesBase.length; i++) {
			columnNames.add(columnNamesBase[i]);
		}
	}

	public void add(SummaryOrganizer summary) {
		Vector<Object> vector = new Vector<Object>();
		for (int i = 0; i < summary.finalVector.size(); i++) {
			for (int j = 0; j < summary.finalVector.get(i).size(); j++) {
				vector.add(summary.finalVector.get(i).get(j));
			}
			data.add(vector);
		}
		organizeColumnNames();
		setDataVector(data, columnNames);
		//printout();
	}

	private void printOut() {
		for (int i = 0; i < columnNames.size(); i++) {
			System.out.print(columnIdentifiers.get(i) + "   ");
		}
		System.out.println();
		System.out.println();
		System.out.println();
		for (int i = 0; i < data.size(); i++) {
			for (int j = 0; j < data.get(i).size(); j++) {
				System.out.print(data.get(i).get(j) + "  ");
			}
			System.out.println();
		}
		System.out.println();
		System.out.println();
		System.out.println();
	}

	public SummaryTableModel(SummaryOrganizer summary) {
		super();
		organizeColumnNames();
		add(summary);
		setDataVector(data, columnNames);
	}

	public SummaryTableModel() {
		super();
	}

}
