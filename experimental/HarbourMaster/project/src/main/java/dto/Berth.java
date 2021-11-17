
package dto;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;


@Entity
@Table(name="berths")
public class Berth {
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    @Enumerated(EnumType.STRING)
    private Float lat;
    private Float lon;

    public Berth(Float lat, Float lon){
        this.lat = lat;
        this.lon = lon;

    }


	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
    }
    public Float getLat() {
		return lat;
	}

	public void setLat(Float lat) {
		this.lat = lat;
    }
    public Float getLon() {
		return lon;
	}

	public void setLon(Float lon) {
		this.lon = lon;
	}
}