
package dto;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;




//ivan

@Entity
@Table(name="berths")
public class Berth {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	private int id;

	private int berthId;

	private double lat;
	private double lon;

	// Empty default constructor needed for H2 in-memory testing DB.
	public Berth() {

	}

	public Berth(int berthId, double lat, double lon) {
		this.berthId = berthId;
		this.lat = lat;
		this.lon = lon;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBerthId() {
		return berthId;
	}

	public void setBerthId(int berthId) {
		this.berthId = berthId;
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
		return getClass().getSimpleName() + "[id=" + id + ", berthId=" + berthId + ", lat=" + lat + ", lon=" + lon + "]";
	}
}