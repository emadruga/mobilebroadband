
import java.io.IOException;

public class Main {
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		/*
		 * Comando de uma possivel tela
		 * 
		 */
		int lenght =9;
		String startTime="2012-01-01", endTime="2013-12-31";
		float Delta1=0.4f, Delta2=0.4f;
		
		//Starts Here
		Core.putValleysOnPentaho_needsAbetterName(startTime, endTime, lenght, Delta1, Delta2);
		//Core.summary(startTime, endTime, lenght, Delta1, Delta2);
	}
	
}