
package org.solent.com504.project.model.dto;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


import org.solent.com504.project.model.dto.Pilot;
import org.solent.com504.project.model.dto.Berth;
import org.solent.com504.project.model.dto.Ship;
import org.solent.com504.project.model.dto.OrderStatus;

import java.sql.Timestamp;

import javax.persistence.Lob;
import javax.persistence.Basic;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.OneToOne;
import javax.persistence.CascadeType;



    @Entity
@Table(name="orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@OneToOne(cascade = {CascadeType.ALL})
	private Ship ship;

	@OneToOne(cascade = {CascadeType.ALL})
	private Pilot pilot;

	@Column(name="orderDate", columnDefinition="TIMESTAMP")
	private LocalDateTime orderDate;

	@Column(name="requestedDate", columnDefinition="TIMESTAMP")
	private LocalDate requestedDate;

	@Column(name="allocatedTime", columnDefinition="TIMESTAMP")
	private LocalDateTime allocatedTime;

	@OneToOne(cascade = {CascadeType.ALL})
	private Berth berth;
	private OrderStatus status;
	private String reason;

	// Empty default constructor needed for H2 in-memory testing DB.
	public Order() {

	}

	// Constructor for order requests with no available pilots.
	public Order(Ship ship, Berth berth, LocalDate requestedDate, OrderStatus status, String reason) {
		this.ship = ship;
		this.berth = berth;
		this.requestedDate = requestedDate;
		this.status = status;
		this.reason = reason;
		orderDate = LocalDateTime.now();
	}

	public Order(Ship ship, Pilot pilot, Berth berth, LocalDate requestedDate, LocalDateTime allocatedTime) {
		this.ship = ship;
		this.pilot = pilot;
		this.berth = berth;
		this.requestedDate = requestedDate;
		this.allocatedTime = allocatedTime;
		orderDate = LocalDateTime.now();
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

	public Pilot getPilot() {
		return pilot;
	}

	public void setPilot(Pilot pilot) {
		this.pilot = pilot;
	}

	public LocalDate getRequestedDate() {
		return requestedDate;
	}

	public void setRequestedDate(LocalDate requestedDate) {
		this.requestedDate = requestedDate;
	}

	public LocalDateTime getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}

	public LocalDateTime getAllocatedTime() {
		return allocatedTime;
	}

	public void setAllocatedTime(LocalDateTime allocatedTime) {
		this.allocatedTime = allocatedTime;
	}

	public Berth getBerth() {
		return berth;
	}

	public void setBerth(Berth berth) {
		this.berth = berth;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Override
	public String toString() {
		String orderDateString = orderDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
		String requestedDateString = requestedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		String allocatedTimeString = allocatedTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

		return "Order [allocatedTime=" + allocatedTimeString + ", berth=" + berth + ", id=" + id
			+ ", orderDate=" + orderDateString + ", pilot=" + pilot + ", requestedDate=" + requestedDateString
			+ ", ship=" + ship + ", status=" + status.name() + ", reason=" + reason + "]";
	}
}

    
