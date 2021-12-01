/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.project.impl.resource.service;

import org.solent.com504.project.model.resource.dao.OrderDAO;
import org.solent.com504.project.model.dto.Order;
import org.solent.com504.project.model.dto.OrderStatus;
import java.util.Random;
import java.util.List;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 *
 * @author jake_
 */
public class OrderService {
    
    //it works
    private transient final org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog(this.getClass());
    
@Autowired
	private OrderDAO orderDAO;

	public Order retrieveOrder(int id) {
		return orderDAO.findById(id);
	}
        
        public Boolean placeOrder(Order order) {
		order.setStatus(OrderStatus.PLACED);

          orderDAO.save(order);
		return true;
	}

public Boolean cancelOrder(int orderId) {
		Order order = orderDAO.findById(orderId);
		if (order == null) {
			return false;
		}

		log.info("Found matching order: " + order + ".");
		order.setStatus(OrderStatus.CANCELLED);

		// Free up the time from the pilot's schedule.

		orderDAO.save(order);
		return true;
	}

	public Boolean requestOrderChange(int orderId) {
		return true;
	}
}


