package it.polito.ai.es2.orm.mapping;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
public class BusLine {

	@Id
	private String line;
	private String description;
	
	@OneToMany(mappedBy="lineId")
	private List<BusLineStop> busLineStop;
	
	public String getLine() {
		return line;
	}
	public void setLine(String line) {
		this.line = line;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@JsonIgnore
	public List<BusLineStop> getBusLineStop() {
		return busLineStop;
	}
	public void setBusLineStop(List<BusLineStop> busLineStop) {
		this.busLineStop = busLineStop;
	}
	
}
