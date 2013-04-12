package tableModel;

import java.util.Vector;

import dataModel.Valley;


public interface IFaceTableModel {
		

	Object columnNames[] = new Object[] {

			};
	
	
	
	
	public Vector<Object> organizeColumnNames();
	
	
	public Vector<Vector<Object>> organizeData(Vector<Valley> valleys);
	
}
