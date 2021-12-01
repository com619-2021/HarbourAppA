package org.solent.com504.project.model.dto;

import io.swagger.annotations.ApiModelProperty;


import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Entity;




@Entity
public class ShipLocation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(hidden = true)
	private int pk;

	private double lat;
	private double lon;

	// Empty default constructor needed for H2 in-memory testing DB.
	public ShipLocation() {

	}

	public ShipLocation(double lat, double lon) {
		this.lat = lat;
		this.lon = lon;
	}

	public int getPk() {
		return pk;
	}

	public void setPk(int pk) {
		this.pk = pk;
	}
	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}

@Override
	public String toString() {
		return getClass().getSimpleName() + "[pk=" + pk + ", lat=" + lat + ", lon=" + lon + "]";
	}
}