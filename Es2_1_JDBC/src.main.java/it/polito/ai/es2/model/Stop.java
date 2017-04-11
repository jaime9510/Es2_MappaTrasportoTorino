package it.polito.ai.es2.model;

import java.util.List;

public class Stop {

	private String id;
	private String name;
	private List<Double> latLng;
	private List<String> lines;
	
	public Stop(String id, String name, List<Double> latLng, List<String> lines) {
		super();
		this.id = id;
		this.name = name;
		this.latLng = latLng;
		this.lines = lines;
	}
	
	public Stop() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Double> getLatLng() {
		return latLng;
	}

	public void setLatLng(List<Double> latLng) {
		this.latLng = latLng;
	}

	public List<String> getLines() {
		return lines;
	}

	public void setLines(List<String> lines) {
		this.lines = lines;
	}
	
}
