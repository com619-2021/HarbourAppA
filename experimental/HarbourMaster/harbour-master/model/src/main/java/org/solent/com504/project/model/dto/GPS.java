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
    private ShipLocation location;

    // Empty default constructor 
    public GPS() {

    }

    public GPS(Ship ship, ShipLocation location) {
        this.ship = ship;
        this.location = location;
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

    public ShipLocation getLocation() {
        return location;
    }

    public void setLocation(ShipLocation location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "GPS{" + "id=" + id + ", ship=" + ship + ", location=" + location + '}';
    }

   
}
