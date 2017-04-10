package it.polito.ai.es2.model;

import java.util.List;

public class LineeFermate {
	
	private List<Linea> linee;
	private List<Stop> stops;
	
	public LineeFermate(List<Linea> linee, List<Stop> stops) {
		super();
		this.linee = linee;
		this.stops = stops;
	}

	public LineeFermate() {
		super();
	}

	public List<Linea> getLinee() {
		return linee;
	}
	public void setLinee(List<Linea> linee) {
		this.linee = linee;
	}
	public List<Stop> getStops() {
		return stops;
	}
	public void setStops(List<Stop> stops) {
		this.stops = stops;
	}
	
	

}
