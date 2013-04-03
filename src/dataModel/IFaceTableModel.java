package dataModel;

import java.util.Vector;


public interface IFaceTableModel {
		

	Object columnNames[] = new Object[] {

			};
	
	
	
	
	public Vector<Object> organizeColumnNames();
	
	
	public Vector<Vector<Object>> organizeData(Vector<Valley> valleys);
	
}
