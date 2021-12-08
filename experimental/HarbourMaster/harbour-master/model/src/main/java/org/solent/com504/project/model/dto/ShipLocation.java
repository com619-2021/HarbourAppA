package org.solent.com504.project.model.dto;

import io.swagger.annotations.ApiModelProperty;


import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Entity;



@Entity
public class ShipLocation {
    

	private Long id;
	private Double lat;
	private Double lon;

	// Empty default constructor needed for H2 in-memory testing DB.
	public ShipLocation() {

	}

	public ShipLocation(Double lat, Double lon) {
		this.lat = lat;
		this.lon = lon;
	}
        
        @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(hidden = true)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public double getLon() {
		return lon;
	}

	public void setLon(Double lon) {
		this.lon = lon;
	}

@Override
	public String toString() {
		return getClass().getSimpleName() + "[id=" + id + ", lat=" + lat + ", lon=" + lon + "]";
	}
}