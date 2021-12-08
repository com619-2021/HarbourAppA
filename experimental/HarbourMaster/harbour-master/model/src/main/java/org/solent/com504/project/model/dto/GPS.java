package org.solent.com504.project.model.dto;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Basic;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.solent.com504.project.model.dto.ShipLocation;
import org.solent.com504.project.model.dto.Ship;



@Entity
@Table(name = "gps")
public class GPS {

    private Integer id;
    private Ship ship;
    private Double lon;
    private Double lat;
   
    
    // Empty default constructor 
    public GPS() {

        
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(hidden = true)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public Double getLon(ShipLocation getLon) {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Double getLat(ShipLocation getLat) {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    @Override
    public String toString() {
        return "GPS{" + "id=" + id + ", ship=" + ship + ", lon=" + lon + ", lat=" + lat + '}';
    }


  
  
}
