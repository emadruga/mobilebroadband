package dataModel;

import java.util.Vector;


public class Valley {

	private Sample valleySample = null;
	private Vector<Sample> samples = new Vector<Sample>();
	private int valleyId = 0;

	public int getValleyId() {
		return valleyId;
	}

	public void setValleyId(int valleyId) {
		this.valleyId = valleyId;
	}

	public Vector<Sample> getAllSamples() {
		return samples;
	}

	public void addSample(Sample sample) {

		this.samples.add(sample);
	}

	public Sample getFirstSample() {
		return this.samples.get(0);
	}

	public Sample getValleySample() {
		return valleySample;
	}

	public void setValleySample(Sample valleySample) {
		this.valleySample = valleySample;
	}

	public Sample getLastSample() {
		return this.samples.get(this.samples.size() - 1);
	}
	
	public int getSessionId() {
		return this.samples.get(0).getSessionId();
	}
	
	public boolean testForCqiProblem(){
		boolean s=false, v=false, e=false;
		if (this.getFirstSample().getCqi() < 12) s=true;
		if (this.getValleySample().getCqi() < 12) v=true;
		if (this.getLastSample().getCqi() < 12) e=true;
		if (((s^v)||(s^e))) return true; else return false;
	}
	
	public boolean testForRscpProblem(){
		boolean s=false, v=false, e=false;
		if (this.getFirstSample().getRscp() < -100) s=true;
		System.out.println(this.getFirstSample().getRscp()+" < -100");

		if (this.getValleySample().getRscp() < -100) v=true;
		if (this.getLastSample().getRscp() < -100) e=true;
		if (((s^v)||(s^e))) return true; else return false;
	}
	
	public boolean testForEcioProblem(){
		boolean s=false, v=false, e=false;
		if (this.getFirstSample().getEcio() < -11) s=true;
		if (this.getValleySample().getEcio() < -11) v=true;
		if (this.getLastSample().getEcio() < -11) e=true;
		if (((s^v)||(s^e))) return true; else return false;
	}
}
