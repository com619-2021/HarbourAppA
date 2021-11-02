package org.solent.com504.project.model.order.dto;

import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import org.solent.com504.project.model.party.dto.Party;
import org.solent.com504.project.model.resource.dto.Resource;
import org.solent.com504.project.model.resource.dto.ResourceAccess;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)

@Entity
public class Order {

    private String href;

    private String uuid;

    private Long id;

    private String name;

    @XmlElementWrapper(name = "subOrdersList")
    @XmlElement(name = "order")
    private List<Order> subOrders;

    private String description;

    private Date orderDate;

    private Date startDate;

    private Date endDate;

    private Party orderOwner;

    private List<OrderChangeRequest> changeRequests;

    private Order parentOrder;

    @XmlElementWrapper(name = "resourceList")
    @XmlElement(name = "resource")
    private List<Resource> resourceOrService;

    private OrderStatus status;

    private ResourceAccess resourceAccess;

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

    @OneToMany
    public List<Order> getSubOrders() {
        return subOrders;
    }

    public void setSubOrders(List<Order> subOrders) {
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

    @OneToMany
    public List<OrderChangeRequest> getChangeRequests() {
        return changeRequests;
    }

    public void setChangeRequests(List<OrderChangeRequest> changeRequests) {
        this.changeRequests = changeRequests;
    }

    @OneToOne
    public Order getParentOrder() {
        return parentOrder;
    }

    public void setParentOrder(Order parentOrder) {
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

}
