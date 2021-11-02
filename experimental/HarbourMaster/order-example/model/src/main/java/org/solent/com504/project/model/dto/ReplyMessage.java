package org.solent.com504.project.model.dto;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import org.solent.com504.project.model.order.dto.Order;
import org.solent.com504.project.model.order.dto.OrderChangeRequest;
import org.solent.com504.project.model.party.dto.Party;
import org.solent.com504.project.model.resource.dto.Resource;
import org.solent.com504.project.model.user.dto.User;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ReplyMessage {

    private Integer code = null;

    private String debugMessage = null;

    private Long totalCount = null;

    private Integer offset = null;

    private Integer limit = null;

    @XmlElementWrapper(name = "stringlist")
    @XmlElement(name = "string")
    private List<String> stringList = null;

    @XmlElementWrapper(name = "partylist")
    @XmlElement(name = "party")
    private List<Party> partyList = null;

    @XmlElementWrapper(name = "userlist")
    @XmlElement(name = "user")
    private List<User> userList = null;

    @XmlElementWrapper(name = "resourceList")
    @XmlElement(name = "resource")
    private List<Resource> resourceList = null;

    @XmlElementWrapper(name = "orderChangeRequestList")
    @XmlElement(name = "orderChangeRequest")
    private List<OrderChangeRequest> orderChangeRequestList;

    @XmlElementWrapper(name = "orderList")
    @XmlElement(name = "order")
    private List<Order> orderList;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDebugMessage() {
        return debugMessage;
    }

    public void setDebugMessage(String debugMessage) {
        this.debugMessage = debugMessage;
    }

    public List<String> getStringList() {
        return stringList;
    }

    public void setStringList(List<String> stringList) {
        this.stringList = stringList;
    }

    public List<Party> getPartyList() {
        return partyList;
    }

    public void setPartyList(List<Party> partyList) {
        this.partyList = partyList;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public List<Resource> getResourceList() {
        return resourceList;
    }

    public void setResourceList(List<Resource> resourceList) {
        this.resourceList = resourceList;
    }

    public List<OrderChangeRequest> getOrderChangeRequestList() {
        return orderChangeRequestList;
    }

    public void setOrderChangeRequestList(List<OrderChangeRequest> orderChangeRequestList) {
        this.orderChangeRequestList = orderChangeRequestList;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }
    
    

}
