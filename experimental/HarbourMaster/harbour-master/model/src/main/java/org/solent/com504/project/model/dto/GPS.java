package org.solent.com504.project.model.dto;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Basic;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.CascadeType;
import javax.persistence.OneToOne;


/**
 *
 * @author jake_
 */



@Entity
public class GPS {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@ApiModelProperty(hidden = true)
	private int id;

	@OneToOne(cascade = {CascadeType.ALL})
	private Ship ship;

	@OneToOne(cascade = {CascadeType.ALL})
	private ShipLocation location;

	// Empty default constructor needed for H2 in-memory testing DB.
	public GPS() {

	}

	public GPS(Ship ship, ShipLocation location) {
		this.ship = ship;
		this.location = location;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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
		return "GPS [id=" + id + ", location=" + location + ", ship=" + ship + "]";
	}
}