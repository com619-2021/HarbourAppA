
package org.solent.com504.project.impl.resource.service;

import java.util.Random;
import java.util.List;
import java.util.Arrays;
import java.util.Random;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.solent.com504.project.model.dto.Ship;
import org.solent.com504.project.model.dto.GPS;
import org.solent.com504.project.model.dto.Order;
import org.solent.com504.project.model.dto.ShipLocation;

import org.solent.com504.project.model.resource.dao.GPSDAO;
import org.solent.com504.project.model.resource.dao.ShipLocationDAO;
import org.solent.com504.project.model.resource.dao.OrderDAO;

@Service
public class GPSService {
	private transient final org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog(this.getClass());

	@Autowired
	private GPSDAO GPSDao;

	@Autowired
	private ShipLocationDAO ShipLocationDAO;

	@Autowired
	private OrderDAO orderDAO;

	public GPS pingPresence(Ship ship) {
		
		GPS existingGPS = GPSDao.findByShipId(ship.getId());
		if (existingGPS != null) {
			return existingGPS;
		}

		
		LocalDateTime time = LocalDateTime.now();
		LocalDateTime earliestTime = time.minusHours(2L);

		Order order = orderDAO.findByShipId(ship.getId());
		LocalDateTime allocatedTime = order.getAllocatedTime();

		
		if (allocatedTime.isAfter(time) && earliestTime.isBefore(time)) {
			
			Random rand = new Random();
			int n = rand.nextInt(3);

			if (n == 1) {
				
				int randomId = rand.nextInt(8);
				ShipLocation location = ShipLocationDAO.findById(randomId);
				GPS GPS = new GPS(ship, location);

				return GPS;
			} else {
				return null;
			}
		} else {
			
			return null;
		}
	}
}

