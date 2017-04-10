package it.polito.ai.es2.model;

import java.util.List;

public class Linea {

	private String line;
	private String desc;
	private List<String> stops;
	
	public Linea(String line, String desc, List<String> stops) {
		super();
		this.line = line;
		this.desc = desc;
		this.stops = stops;
	}

	public Linea() {
	}

	public String getLine() {
		return line;
	}
	public void setLine(String line) {
		this.line = line;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public List<String> getStops() {
		return stops;
	}
	public void setStops(List<String> stops) {
		this.stops = stops;
	}
	
}
