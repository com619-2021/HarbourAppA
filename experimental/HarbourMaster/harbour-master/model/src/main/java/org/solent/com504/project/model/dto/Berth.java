
package org.solent.com504.project.model.dto;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;



@Entity
@Table(name="berths")
public class Berth {
	

	private Long id;
	private Integer berthId;
	private Double lat;
	private Double lon;

	// Empty default constructor needed for H2 in-memory testing DB.
	public Berth() {

	}

	public Berth(Integer berthId, Double lat, Double lon) {
		this.berthId = berthId;
		this.lat = lat;
		this.lon = lon;
	}

        @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getBerthId() {
		return berthId;
	}

	public void setBerthId(Integer berthId) {
		this.berthId = berthId;
	}


	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLon() {
		return lon;
	}

	public void setLon(Double lon) {
		this.lon = lon;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "[id=" + id + ", berthId=" + berthId + ", lat=" + lat + ", lon=" + lon + "]";
	}
}