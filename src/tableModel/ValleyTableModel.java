package tableModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import dataModel.Valley;

	

public class ValleyTableModel extends DefaultTableModel implements IFaceTableModel{
	
	public Vector<Vector<Object>> data = new Vector<Vector<Object>>();
	public Vector<Object> names = new Vector<Object>();
	
	
	public ValleyTableModel(Vector<Valley> valleys) {
		super();
		names=organizeColumnNames();
		data=organizeData(valleys);
		
		setDataVector(data, names);
		printOut();
	}
	

	Object columnNames[] = new Object[] {
			"msgId",
			"msgDate",
			"msgTime",
			"throughput" ,
			"speed",
			"sessionId",
			"valleyId",
			"vlySampleId",
			
			"cqi",
			"rscp",
			"sc1",
			"primScCode",
			"ecio",
			
			"cqiProb",
			"rscpProb",
			"ecioProb"
			};
	
	
	
	
	public Vector<Object> organizeColumnNames() {
		Vector<Object> names = new Vector<Object>();
		for (int i = 0; i < columnNames.length; i++) {
			names.add(columnNames[i]);
		}
		return names;
	}
	
	
	public Vector<Vector<Object>> organizeData(Vector<Valley> valleys) {
		Vector<Vector<Object>> finalVector = new Vector<Vector<Object>>();
		DateFormat justDateFormatter = new SimpleDateFormat("dd/MM/yyyy");
		DateFormat justTimeFormatter = new SimpleDateFormat("HH:mm:ss");
		Vector<Object> vector = new Vector<Object>();
		for (int i = 0; i < valleys.size(); i++) {
			
			for (int j = 0; j < valleys.get(i).getAllSamples().size(); j++) {
				vector = new Vector<Object>();
				vector.add(valleys.get(i).getAllSamples().get(j).getMsgId());
				vector.add(justDateFormatter.format(valleys.get(i).getAllSamples().get(j).getMsgTime()));
				vector.add(justTimeFormatter.format(valleys.get(i).getAllSamples().get(j).getMsgTime()));
				vector.add(valleys.get(i).getAllSamples().get(j).getThroughput());
				vector.add(valleys.get(i).getAllSamples().get(j).getSpeed());
				vector.add(valleys.get(i).getSessionId());
				vector.add(valleys.get(i).getValleyId());
				vector.add(valleys.get(i).getValleySample().getMsgId());
				
				vector.add(valleys.get(i).getAllSamples().get(j).getCqi());
				vector.add(valleys.get(i).getAllSamples().get(j).getRscp());
				vector.add(valleys.get(i).getAllSamples().get(j).getSc1());
				vector.add(valleys.get(i).getAllSamples().get(j).getPrimScCode());
				vector.add(valleys.get(i).getAllSamples().get(j).getEcio());
				
				vector.add(valleys.get(i).testForCqiProblem());
				vector.add(valleys.get(i).testForRscpProblem());
				vector.add(valleys.get(i).testForEcioProblem());
				finalVector.add(vector);
			
			}
			
			
		}	
		return finalVector;
	}
	
	public void printOut() {
		for (int i = 0; i < names.size(); i++) {
			System.out.print(names.get(i)+"  ");
		}
		System.out.println();
		for (int i = 0; i < data.size(); i++) {
			for (int j = 0; j < data.get(i).size(); j++) {
				System.out.print(data.get(i).get(j)+"  ");
			}
			System.out.println();
		}
	}
	
}
