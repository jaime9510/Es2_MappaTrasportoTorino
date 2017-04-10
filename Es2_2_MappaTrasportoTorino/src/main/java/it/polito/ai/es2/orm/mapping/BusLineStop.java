package it.polito.ai.es2.orm.mapping;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class BusLineStop implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private String stopId;
	
	@Id
	private String lineId;
	private Short seqenceNumber;
	
	public String getStopId() {
		return stopId;
	}
	public void setStopId(String stopId) {
		this.stopId = stopId;
	}
	public String getLineId() {
		return lineId;
	}
	public void setLineId(String lineId) {
		this.lineId = lineId;
	}
	public Short getSeqenceNumber() {
		return seqenceNumber;
	}
	public void setSeqenceNumber(Short seqenceNumber) {
		this.seqenceNumber = seqenceNumber;
	}
	
	
	
}
