package dataModel;
import java.sql.Timestamp;

/**
 * 
 * @author User
 */
public class Sample {
	
	private int msgId = 0; 
	private int sessionId = 0; 
	private Timestamp msgTime = null; 
	private int latitude = 0; 
	private int longitude = 0;
	private int throughput = 0; 
	private int speed = 0; 
	private int cqi = 0; 
	private int rscp= 0; 
	private int sc1=0;
	private int primScCode=0;
	private int ecio=0;
	
	public int getMsgId() {
		return msgId;
	}
	
	public void setMsgId(int msgId) {
		this.msgId = msgId;
	}
	
	public int getSessionId() {
		return sessionId;
	}
	
	public void setSessionId(int sessionId) {
		this.sessionId = sessionId;
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
		
	public int getCqi() {
		return cqi;
	}
	
	public void setCqi(int cqi) {
		this.cqi = cqi;
	}
	
	public int getRscp() {
		return rscp;
	}
	
	public void setRscp(int rscp) {
		this.rscp = rscp;
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

	
	public int getEcio() {
		return ecio;
	}

	
	public void setEcio(int ecio) {
		this.ecio = ecio;
	}

	
	
		
}
