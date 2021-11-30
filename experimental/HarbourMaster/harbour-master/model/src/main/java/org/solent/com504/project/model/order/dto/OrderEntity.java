package org.solent.com504.project.model.order.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import org.solent.com504.project.model.party.dto.Party;
import org.solent.com504.project.model.resource.dto.Resource;
import org.solent.com504.project.model.resource.dto.ResourceAccess;
import org.solent.com504.project.model.utilities.OrderToJsonConverter;

@Entity
public class OrderEntity {

    private String href;

    private String uuid;

    private Long id;

    private String name;

    private List<OrderEntity> subOrders  = new ArrayList<OrderEntity>();

    private String description;

    private Date orderDate;

    private Date startDate;

    private Date endDate;

    private Party orderOwner;

    private List<OrderChangeRequestEntity> changeRequests = new ArrayList<OrderChangeRequestEntity>(); 

    private OrderEntity parentOrder;

    private List<Resource> resourceOrService = new ArrayList<Resource>();

    private OrderStatus status;

    private ResourceAccess resourceAccess;
    
    // holds an external order as a json string - used for references
    private Order externalOrder;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//TODO real problem here - swagger does a recursive list and fails to load this data type
// see https://stackoverflow.com/questions/59598383/swagger-recursively-resolving-dependencies-for-type-infinite-loop
// https://github.com/springfox/springfox/issues/621 Cycles in Java classes cause infinite loop in ModelAttributeParameterExpander
    @OneToMany
    public List<OrderEntity> getSubOrders() {
        return subOrders;
    }

    public void setSubOrders(List<OrderEntity> subOrders) {
        this.subOrders = subOrders;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @OneToOne
    public Party getOrderOwner() {
        return orderOwner;
    }

    public void setOrderOwner(Party orderOwner) {
        this.orderOwner = orderOwner;
    }

    // REMOVES order change requests if order is deleted
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "orderchangerequests_id")
    public List<OrderChangeRequestEntity> getChangeRequests() {
        return changeRequests;
    }

    public void setChangeRequests(List<OrderChangeRequestEntity> changeRequests) {
        this.changeRequests = changeRequests;
    }

    @OneToOne
    public OrderEntity getParentOrder() {
        return parentOrder;
    }

    public void setParentOrder(OrderEntity parentOrder) {
        this.parentOrder = parentOrder;
    }

    @OneToMany
    public List<Resource> getResourceOrService() {
        return resourceOrService;
    }

    public void setResourceOrService(List<Resource> resourceOrService) {
        this.resourceOrService = resourceOrService;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public ResourceAccess getResourceAccess() {
        return resourceAccess;
    }

    public void setResourceAccess(ResourceAccess resourceAccess) {
        this.resourceAccess = resourceAccess;
    }

    // this avoids having to create tables for the external order
    // but at the expense of not being able to search on characterists 
    @Column(name = "externalorder", length = 1000)
    @Convert(converter = OrderToJsonConverter.class)
    public Order getExternalOrder() {
        return externalOrder;
    }


    public void setExternalOrder(Order externalOrder) {
        this.externalOrder = externalOrder;
    }
    
    

    @Override
    public String toString() {
        return "OrderEntity{" + "href=" + href + ", uuid=" + uuid + ", id=" + id + ", name=" + name + ", subOrders=" + subOrders + ", description=" + description + ", orderDate=" + orderDate + ", startDate=" + startDate + ", endDate=" + endDate + ", orderOwner=" + orderOwner + ", parentOrder=" + parentOrder + ", resourceOrService=" + resourceOrService + ", status=" + status + ", resourceAccess=" + resourceAccess + '}';
    }
  

}
