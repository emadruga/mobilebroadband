package dataModel;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class SummaryTableModel extends DefaultTableModel implements IFaceTableModel{
	
	public SummaryTableModel(Vector<Valley> valleys) throws IOException{
		super();
		setDataVector(organizeData(valleys), organizeColumnNames());
	}	

	Object columnNames[] = new Object[] {
			"all",
			"cqi",
			"ecio",
			"rscp",
			"cqiAecio",
			"cqiArscp",
			"ecioArscp",
			"nothing"
			};
	private int all;
	private int cqi;
	private int ecio;
	private int rscp;
	private int cqiAecio;
	private int cqiArscp;
	private int ecioArscp;
	private int nothing;

	public Vector<Object> organizeColumnNames() {
		Vector<Object> names = new Vector<Object>();
		for (int i = 0; i < columnNames.length; i++) {
			names.add(columnNames[i]);
		}
		return names;
	}
	
	public Vector<Vector<Object>> organizeData(Vector<Valley> valleys) {
		
		for (Valley valley : valleys) {
			if (valley.testForCqiProblem() && valley.testForEcioProblem() && valley.testForRscpProblem()) all++;
			if (valley.testForCqiProblem() && !valley.testForEcioProblem() && !valley.testForRscpProblem()) cqi++;
			if (!valley.testForCqiProblem() && valley.testForEcioProblem() && !valley.testForRscpProblem()) ecio++;
			if (!valley.testForCqiProblem() && !valley.testForEcioProblem() && valley.testForRscpProblem()) rscp++;
			if (valley.testForCqiProblem() && valley.testForEcioProblem() && !valley.testForRscpProblem()) cqiAecio++;
			if (valley.testForCqiProblem() && !valley.testForEcioProblem() && valley.testForRscpProblem()) cqiArscp++;
			if (!valley.testForCqiProblem() && valley.testForEcioProblem() && valley.testForRscpProblem()) ecioArscp++;
			if (!valley.testForCqiProblem() && !valley.testForEcioProblem() && !valley.testForRscpProblem()) nothing++;
		}
		
		System.out.println("All: "+all);
		System.out.println("Cqi: "+cqi);
		System.out.println("ecio: "+ecio);
		System.out.println("Rscp: "+rscp);
		System.out.println("Cqi and Eci0: "+cqiAecio);
		System.out.println("Cqi and Rscp: "+cqiArscp);
		System.out.println("Eci0 and Rscp: "+ecioArscp);
		
		Vector<Vector<Object>> finalVector = new Vector<Vector<Object>>();
		Vector<Object> vector = new Vector<Object>();
		vector.add(all);
		vector.add(cqi);
		vector.add(ecio);
		vector.add(rscp);
		vector.add(cqiAecio);
		vector.add(cqiArscp);
		vector.add(ecioArscp);
		vector.add(nothing);
		finalVector.add(vector);
		return finalVector;
	}	
}
