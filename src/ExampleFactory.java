public class ExampleFactory {	
	public ExampleTableModel getAllData()  {
	    ExampleTableModel tbm = null;
	    try {
		tbm = new ExampleTableModel();
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	    return tbm;
	}

}
