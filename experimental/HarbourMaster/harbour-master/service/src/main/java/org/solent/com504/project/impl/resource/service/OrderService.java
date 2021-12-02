/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.project.impl.resource.service;

import java.time.LocalDate;
import org.solent.com504.project.impl.dao.dao.OrderDAO;
import org.solent.com504.project.model.dto.Order;
import org.solent.com504.project.model.dto.OrderStatus;


import org.solent.com504.project.impl.dao.dao.PilotDAO;
import org.solent.com504.project.impl.dao.dao.TideDAO;
import org.solent.com504.project.model.dto.Pilot;
import org.solent.com504.project.model.dto.Ship;
import org.solent.com504.project.model.dto.Tide;


import java.util.Random;
import java.util.List;
import java.util.Arrays;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



//needs code for placing order, cancelling order
//scheduling pilot??


@Service
public class OrderService {
    
    //it works
    private transient final org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog(this.getClass());
    

	@Autowired
	private OrderDAO orderDAO;

	@Autowired
	private TideDAO tideDAO;

	@Autowired
	private PilotDAO pilotDAO;

	public Order retrieveOrder(UUID uuid) {
		return orderDAO.findByUUID(uuid);
	}

	public List<Order> findAll() {
		return orderDAO.findAll();
	}

   public Order placeOrder(Order order, List<Pilot> pilots) {
		if (pilots == null) {
			order.setStatus(OrderStatus.DENIED);
			//order.setReason("No pilots are available.");
			orderDAO.save(order);
			return order;
		}

		LocalDate date = order.getDayOfArrival();
		Ship ship = order.getShip();

		List<Tide> safeTides = tideDAO.getSafeTidesOnDay(date.getDayOfWeek(), ship.getDraft());

		//Pilot chosenPilot = schedulePilot(pilots, safeTides, order, order.getDayOfArrival(), true);

//		if (chosenPilot == null) {
//			order.setStatus(OrderStatus.DENIED);
//			order.setReason("No pilots are available.");
//			orderDAO.save(order);
//			return order;
//		}


		return order;
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


