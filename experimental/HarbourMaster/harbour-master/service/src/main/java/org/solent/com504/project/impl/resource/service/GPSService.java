
package org.solent.com504.project.impl.resource.service;

import java.util.Random;
import java.util.List;
import java.util.Arrays;
import java.util.Random;
  //not sure how GPS service will work yet.
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.solent.com504.project.model.dto.Ship;
import org.solent.com504.project.model.dto.GPS;
import org.solent.com504.project.model.dto.Order;
import org.solent.com504.project.model.dto.OrderStatus;
import org.solent.com504.project.model.dto.ShipLocation;


import org.solent.com504.project.impl.dao.dao.GPSDAO;
import org.solent.com504.project.impl.dao.dao.ShipLocationDAO;
import org.solent.com504.project.impl.dao.dao.OrderDAO;

@Service
public class GPSService {
	private transient final org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog(this.getClass());

        @Autowired
	private GPSDAO GPSDao;

	@Autowired
	private ShipLocationDAO shipLocationDAO;

	@Autowired
	private OrderDAO orderDAO;
        
        
//        copied from grp B as test needed a bean to pass test to build
//        
     public GPS pingPresence(UUID shipUUID) {
		/* checks to see whether the location of this ship has already
		   been found. */
		GPS existingGPS = GPSDao.findByShipUUID(shipUUID);
		if (existingGPS != null) {
			return existingGPS;
		}

		/* a ship may only be waiting, at most, two hours before their
		   allocated time of arrival. */
		LocalDateTime time = LocalDateTime.now();
		LocalDateTime earliestTime = time.minusHours(2L);

		Order order = orderDAO.findConfirmedByShipUUID(shipUUID);

		/* ships with no orders or orders that have been denied
		   or cancelled will not come to port. */
		if (order == null || order.getStatus() == OrderStatus.DENIED || order.getStatus() == OrderStatus.CANCELLED) {
			return null;
		}
                LocalDateTime allocatedStart = order.getAllocatedStart();

		/* checks to see whether the current time is between the time that
		   the order has been allocated and the absolute earliest that they
		   may be waiting. */
		if (allocatedStart.isAfter(time) && earliestTime.isBefore(time)) {
			/* randomises the chance of a ship appearing. not every ping
			   means a ship will be waiting, adding some variance. */
			Random rand = new Random();
			int n = rand.nextInt(3); // 1 in 3 chance.

			if (n == 1) {
				/* makes a random id to select from the waiting_location table. */
				int randomId = 3; // had problems with rand.nextInt w/ wldao.count(); will fix.
				ShipLocation location = shipLocationDAO.findById(randomId);
				GPS GPS = new GPS(order.getShip(), location);

				return GPS;
			} else {
				return null;
			}
		} else {
			/* ship can't possibly be waiting, hence return null */
			return null;
		}
	}
        
           
}

      
               
                

        