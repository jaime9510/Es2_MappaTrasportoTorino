package it.polito.ai.es2.dao;

import java.util.List;

import it.polito.ai.es2.orm.mapping.BusStop;

public interface BusLineStopInterface {

	public List<BusStop> getBusLineStops(String line);
	
}
