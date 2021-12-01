package org.solent.com504.project.model.dto;

import org.solent.com504.project.model.dto.Ship;
import org.solent.com504.project.model.dto.Berth;

import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;


public class PilotBookingRequest {
	private Ship ship;
	private LocalDate date;
	private Berth berth;

	// Empty default constructor needed for H2 in-memory testing DB.
	public PilotBookingRequest() {

	}

	public PilotBookingRequest(Ship ship, LocalDate date, Berth berth) {
		this.ship = ship;
		this.date = date;
		this.berth = berth;
	}

	public Ship getShip() {
		return ship;
	}

	public void setShip(Ship ship) {
		this.ship = ship;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Berth getBerth() {
		return berth;
	}

	public void setBerth(Berth berth) {
		this.berth = berth;
	}

	@Override
	public String toString() {
		String dateString = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

		return getClass().getSimpleName() + "[date=" + dateString + ", ship=" + ship + "]";
	}
}