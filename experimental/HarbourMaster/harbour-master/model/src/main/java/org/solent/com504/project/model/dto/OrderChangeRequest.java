package org.solent.com504.project.model.dto;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//import com.devops.groupb.harbourmaster.dto.Order;
//import com.devops.groupb.harbourmaster.dto.OrderChangeStatus;

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

@Entity
public class OrderChangeRequest {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@OneToOne
	private Order order;
	@Column(name="requestDate", columnDefinition="TIMESTAMP")
	private LocalDateTime requestDate;
	private String reason;
	private OrderChangeStatus status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public LocalDateTime getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(LocalDateTime requestDate) {
		this.requestDate = requestDate;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public OrderChangeStatus getStatus() {
		return status;
	}

	public void setStatus(OrderChangeStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		String dateString = requestDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

		return getClass().getSimpleName() + "[id=" + id + ", order=" + order + ", reason=" + reason + ", requestDate="
			+ dateString + ", status=" + status.name() + "]";
	}
        
}