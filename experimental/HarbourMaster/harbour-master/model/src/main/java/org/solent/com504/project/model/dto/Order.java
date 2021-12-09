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
    

    
      
     private String uuid = UUID.randomUUID().toString();

     private Long id;

        
    private OrderStatus status;

   
    private LocalDateTime orderDate;
 
    private LocalDate dayOfArrival;
  
    private LocalDateTime allocatedStart;
  
    private LocalDateTime allocatedEnd;

   
   
      
    //table doesn't like being given an entire object.
        private Long shipID;
        private Long pilotID;
        
//would need to get berthID from port owners
        private Long berthID;

      
        
    private LocalDateTime allocatedTime;
    private LocalDate requestedDate;
    
    
    

    // Empty default constructor - hibernate/ h2
    public Order() {

    }

   

    public Order(String uuid, Long shipID, Long pilotID, Long berthID, LocalDate requestedDate, LocalDateTime allocatedTime) {
        this.shipID = shipID;
        this.pilotID = pilotID;
        this.berthID = berthID;
        this.requestedDate = requestedDate;
        this.allocatedTime = allocatedTime;
        this.orderDate = LocalDateTime.now();
    }
//order created and when pilot will be unavailable.
    
    
    
    @Id
     public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
        
    public Long getId(Ship getID) {
        return shipID;
    }
    public Long getId(Pilot getID) {
        return pilotID;
    }
    public Long getId(Berth getID) {
        return berthID;
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

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }




}
