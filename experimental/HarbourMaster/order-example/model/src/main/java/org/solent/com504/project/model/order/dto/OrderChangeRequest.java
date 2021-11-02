package org.solent.com504.project.model.order.dto;

import java.util.Date;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import org.solent.com504.project.model.party.dto.Party;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)

@Entity
public class OrderChangeRequest {

    private Order changeRequest;

    private Date requestDate;

    private Date approvedDate;

    private ChangeStatus status;

    private String changeReason;

    private Party changeRequestor;

    private String responseDescription;

    private Long id;

    private String uuid;

    private String href;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // not referring to table entry since this is just an update dto
    @Embedded
    public Order getChangeRequest() {
        return changeRequest;
    }

    public void setChangeRequest(Order changeRequest) {
        this.changeRequest = changeRequest;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public Date getApprovedDate() {
        return approvedDate;
    }

    public void setApprovedDate(Date approvedDate) {
        this.approvedDate = approvedDate;
    }

    public ChangeStatus getStatus() {
        return status;
    }

    public void setStatus(ChangeStatus status) {
        this.status = status;
    }

    public String getChangeReason() {
        return changeReason;
    }

    public void setChangeReason(String changeReason) {
        this.changeReason = changeReason;
    }

    @OneToOne
    public Party getChangeRequestor() {
        return changeRequestor;
    }

    public void setChangeRequestor(Party changeRequestor) {
        this.changeRequestor = changeRequestor;
    }

    public String getResponseDescription() {
        return responseDescription;
    }

    public void setResponseDescription(String responseDescription) {
        this.responseDescription = responseDescription;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

}
