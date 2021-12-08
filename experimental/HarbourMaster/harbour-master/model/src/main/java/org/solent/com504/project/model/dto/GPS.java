package org.solent.com504.project.model.dto;

import io.swagger.annotations.ApiModelProperty;
import java.util.UUID;

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


    //ship
    private UUID uuid = null;
    private Double draft;
    //shiplocation
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


    
    
     public UUID getUuid(Ship getUuid) {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Double getDraft(Ship getDraft) {
        return draft;
    }

    public void setDraft(Double draft) {
        this.draft = draft;
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
        return "GPS{" + "id=" + id + ", uuid=" + uuid + ", draft=" + draft + ", lon=" + lon + ", lat=" + lat + '}';
    }

 

  
  
}
