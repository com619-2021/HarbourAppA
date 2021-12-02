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

import java.util.List;
import java.util.UUID;


import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "orders")
public class Order {
    

    //breaks if moved
      @Id
     private String uuid = UUID.randomUUID().toString();

    private OrderStatus status;

    private String reason;
  
    private LocalDateTime orderDate;
 
    private LocalDate dayOfArrival;
  
    private LocalDateTime allocatedStart;
  
    private LocalDateTime allocatedEnd;

   
    //private List<UUID> changeRequests;

        private Ship ship;
        private Pilot pilot;
        private Berth berth;

      
    private LocalDateTime allocatedTime;
    private LocalDate requestedDate;

    // Empty default constructor - hibernate/ h2
    public Order() {

    }

    // Constructor for order requests with no available pilots.
    public Order(Ship ship, Berth berth, LocalDate dayOfArrival) {
        this.uuid = UUID.randomUUID().toString();
        this.ship = ship;
        this.berth = berth;
        this.dayOfArrival = dayOfArrival;
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

    
    
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

   
    public LocalDateTime getAllocatedEnd() {
        return allocatedEnd;
    }

    public void setAllocatedEnd(LocalDateTime allocatedEnd) {
        this.allocatedEnd = allocatedEnd;
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

//
//    public List<UUID> getChangeRequests() {
//        return changeRequests;
//    }
//
//    public void setChangeRequests(List<UUID> changeRequests) {
//        this.changeRequests = changeRequests;
//    }



    @Override
    public String toString() {
        return "Order{" + "uuid=" + uuid + ", status=" + status + ", reason=" + reason + ", orderDate=" + orderDate + ", dayOfArrival=" + dayOfArrival + ", allocatedStart=" + allocatedStart + ", allocatedEnd=" + allocatedEnd + ", ship=" + ship + ", pilot=" + pilot + ", berth=" + berth + ", allocatedTime=" + allocatedTime + ", requestedDate=" + requestedDate + '}';
    }
}
