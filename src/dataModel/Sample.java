package dataModel;

import java.sql.Timestamp;

/**
 * 
 * @author User
 */
public class Sample {

	// Session / Data packet parameters
	private int				ftp_msgId		= 0;
	private int				sessionid		= 0;
	private Timestamp	msgTime			= null;

	// Position Parameters
	private int				latitude		= 0;
	private int				longitude		= 0;
	private int				throughput	= 0;
	private int				speed				= 0;

	// Link Parameters
	private int				sc1					= 0;
	private int				sc2					= 0;
	private int				sc3					= 0;
	private int				primScCode	= 0;
	private int				mnc					= 0;

	// RF parameters
	private double		ecio				= 0;
	private double		cqi					= 0;
	private double		rscp				= 0;

	/*
	 * Methods:
	 */
	public String getOperatorName() {
		String timName = "TIM", claroName = "CLARO", vivoName = "VIVO", oiName = "OI";

		String name = null;
		switch (this.mnc) {
			case 2:
				name = timName;
				break;
			case 5:
				name = claroName;
				break;
			case 11:
				name = vivoName;
				break;
			case 31:
				name = oiName;
				break;
			default:
				System.out.println("There's no MNC that matches. Verify Sample.getOperatorName");
				System.exit(0);
				break;
		}
		return name;
	}

	/*
	 * Getters and Setters
	 */

	public int getMsgId() {
		return ftp_msgId;
	}

	public void setMsgId(int msgId) {
		this.ftp_msgId = msgId;
	}

	public int getSessionId() {
		return sessionid;
	}

	public void setSessionId(int sessionId) {
		this.sessionid = sessionId;
	}

	public Timestamp getMsgTime() {
		return msgTime;
	}

	public void setMsgTime(Timestamp msgTime) {
		this.msgTime = msgTime;
	}

	public int getLatitude() {
		return latitude;
	}

	public void setLatitude(int latitude) {
		this.latitude = latitude;
	}

	public int getLongitude() {
		return longitude;
	}

	public void setLongitude(int longitude) {
		this.longitude = longitude;
	}

	public int getThroughput() {
		return throughput;
	}

	public void setThroughput(int throughput) {
		this.throughput = throughput;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public double getCqi() {
		return cqi;
	}

	public void setCqi(double d) {
		this.cqi = d;
	}

	public double getRscp() {
		return rscp;
	}

	public void setRscp(double d) {
		this.rscp = d;
	}

	public int getSc1() {
		return sc1;
	}

	public void setSc1(int sc1) {
		this.sc1 = sc1;
	}

	public int getPrimScCode() {
		return primScCode;
	}

	public void setPrimScCode(int primScCode) {
		this.primScCode = primScCode;
	}

	public double getEcio() {
		return ecio;
	}

	public void setEcio(double d) {
		this.ecio = d;
	}

	public int getSc2() {
		return sc2;
	}

	public void setSc2(int sc2) {
		this.sc2 = sc2;
	}

	public int getSc3() {
		return sc3;
	}

	public void setSc3(int sc3) {
		this.sc3 = sc3;
	}

	public int getMnc() {
		return mnc;
	}

	public void setMnc(int mnc) {
		this.mnc = mnc;
	}

}
