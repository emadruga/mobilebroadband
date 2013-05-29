package dataModel;

import java.util.Vector;

public class Valley {

	private Sample					valleySample	= null;
	private Vector<Sample>	samples				= new Vector<Sample>();
	private int							valleyId			= 0;

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

	/*
	 * Tests of radio problems (below). Each one verifies if the value of RF parameter is acceptable in "start sample", "valley sample" and "end sample" (s/v/e).
	 * 
	 * The evaluation for s, v and e is made: --T/T/T or F/F/F returns "False": (It's not an "influence"). --Any other combination returns "True" (It's an "influence").
	 * 
	 * Evaluation: ((start XOR valley) OR (start XOR end))
	 */

	public boolean testForCqiProblem() {

		boolean rsp = false;
		for (Sample sample : this.getAllSamples()) {
			if (sample.getCqi() < 15)
				rsp = true;
		}

		/*
		 * for (int i = idS; i < idE; i++) { if (this.getAllSamples().get(i).getCqi() < 12) rsp=true; }
		 */
		return rsp;

		/*
		 * boolean s = false, v = false, e = false; if (this.getFirstSample().getCqi() < 12) s = true; if (this.getValleySample().getCqi() < 12) v = true; if
		 * (this.getLastSample().getCqi() < 12) e = true; if ((s ^ v) || (s ^ e)) return true; else return false;
		 */

	}

	public boolean testForRscpProblem() {

		boolean rsp = false;
		for (Sample sample : this.getAllSamples()) {
			if (sample.getRscp() < -100)
				rsp = true;
		}
		return rsp;

		/*
		 * boolean s = false, v = false, e = false; if (this.getFirstSample().getRscp() < -100) s = true; if (this.getValleySample().getRscp() < -100) v = true; if
		 * (this.getLastSample().getRscp() < -100) e = true; if ((s ^ v) || (s ^ e)) return true; else return false;
		 */}

	public boolean testForEcioProblem() {

		boolean rsp = false;

		for (Sample sample : this.getAllSamples()) {
			if (sample.getEcio() < -12)
				rsp = true;
		}

		return rsp;

		/*
		 * boolean s = false, v = false, e = false; if (this.getFirstSample().getEcio() < -11) s = true; if (this.getValleySample().getEcio() < -11) v = true; if
		 * (this.getLastSample().getEcio() < -11) e = true; if ((s ^ v) || (s ^ e)) return true; else return false;
		 */}

	public boolean testForMovement() {

		boolean rsp = false;
		for (int i = 0; i < this.samples.size(); i++) {
			if (this.samples.get(i).getSpeed() > 0)
				rsp = true;
		}
		return rsp;
	}

	public boolean testForScChange() {
		boolean rsp = false;
		for (int i = 1; i < this.samples.size(); i++) {
			int prevSc = this.samples.get(i - 1).getPrimScCode();
			int postSc = this.samples.get(i).getPrimScCode();
			if (prevSc != postSc)
				rsp = true;
		}
		return rsp;
	}
}
