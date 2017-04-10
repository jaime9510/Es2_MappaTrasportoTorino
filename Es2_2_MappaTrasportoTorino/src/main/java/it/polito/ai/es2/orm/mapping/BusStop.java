package it.polito.ai.es2.orm.mapping;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class BusStop {
	
	@Id
	private String id;
	private String name;
	private Double lat;
	private Double lng;
	
	@OneToMany(mappedBy="stopId")
	private List<BusLineStop> busLineStop;
	
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
	public Double getLat() {
		return lat;
	}
	public void setLat(Double lat) {
		this.lat = lat;
	}
	public Double getLng() {
		return lng;
	}
	public void setLng(Double lng) {
		this.lng = lng;
	}
	public List<BusLineStop> getBusLineStop() {
		return busLineStop;
	}
	public void setBusLineStop(List<BusLineStop> busLineStop) {
		this.busLineStop = busLineStop;
	}
	
}
