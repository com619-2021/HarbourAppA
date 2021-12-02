package org.solent.com504.project.model.dto;

import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.solent.com504.project.model.dto.Pilot;
import org.solent.com504.project.model.dto.Berth;
import org.solent.com504.project.model.dto.Ship;
import org.solent.com504.project.model.dto.OrderStatus;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

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
import javax.persistence.ElementCollection;
import javax.persistence.Transient;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(hidden = true)
    private Integer id;

    @GeneratedValue(generator = "UUID")
    private UUID uuid;

    private OrderStatus status;

    private String reason;

    @Column(name = "orderDate", columnDefinition = "TIMESTAMP")
    private LocalDateTime orderDate;

    @Column(name = "dayOfArrival", columnDefinition = "TIMESTAMP")
    private LocalDate dayOfArrival;

    @Column(name = "allocatedStart", columnDefinition = "TIMESTAMP")
    private LocalDateTime allocatedStart;

    @Column(name = "allocatedEnd", columnDefinition = "TIMESTAMP")
    private LocalDateTime allocatedEnd;

    @ElementCollection
    private List<UUID> changeRequests;

    @OneToOne(cascade = {CascadeType.ALL})
    private Ship ship;

    @OneToOne(cascade = {CascadeType.ALL})
    private Pilot pilot;

    @OneToOne(cascade = {CascadeType.ALL})
    private Berth berth;

    @Transient
    private final double[] fares = {100, 200, 300};
    private LocalDateTime allocatedTime;
    private LocalDate requestedDate;

    // Empty default constructor - hibernate/ h2
    public Order() {

    }

    // Constructor for order requests with no available pilots.
    public Order(Ship ship, Berth berth, LocalDate dayOfArrival) {
        this.uuid = UUID.randomUUID();
        this.ship = ship;
        this.berth = berth;
        this.dayOfArrival = dayOfArrival;
        orderDate = LocalDateTime.now();

    }

    public Order(Ship ship, Pilot pilot, Berth berth, LocalDate requestedDate, LocalDateTime allocatedTime) {
        this.ship = ship;
        this.pilot = pilot;
        this.berth = berth;
        //add this.requestedDate = requestedDate;
        //add this.allocatedTime = allocatedTime;
        orderDate = LocalDateTime.now();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public LocalDateTime getAllocatedEnd() {
        return allocatedEnd;
    }

    public void setAllocatedEnd(LocalDateTime allocatedEnd) {
        this.allocatedEnd = allocatedEnd;
    }

    public UUID getUUID() {
        return uuid;
    }

    public void setUUID(UUID uuid) {
        this.uuid = uuid;
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

    public LocalDate getDayOfArrival() {
        return dayOfArrival;
    }

    public void setDayOfArrival(LocalDate dayOfArrival) {
        this.dayOfArrival = dayOfArrival;
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

    public LocalDateTime getAllocatedStart() {
        return allocatedStart;
    }

    public void setAllocatedStart(LocalDateTime allocatedStart) {
        this.allocatedStart = allocatedStart;
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

    public List<UUID> getChangeRequests() {
        return changeRequests;
    }

    public void setChangeRequests(List<UUID> changeRequests) {
        this.changeRequests = changeRequests;
    }

    @Override
    public String toString() {
        String orderDateString = orderDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        String dayOfArrivalString = dayOfArrival.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        String allocatedStartString = "";
        String allocatedEndString = "";

        if (allocatedStart != null && allocatedEndString != null) {
            allocatedStartString = allocatedStart.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            allocatedEndString = allocatedEnd.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        }

        return "Order [allocatedStart=" + allocatedStartString + ", allocatedEnd=" + allocatedEndString + ", berth=" + berth + ", id=" + id
                + ", uuid=" + uuid + ", orderDate=" + orderDateString + ", pilot=" + pilot + ", dayOfArrival=" + dayOfArrivalString
                + ", ship=" + ship + ", status=" + status.name() + ", reason=" + reason;
    }
}
