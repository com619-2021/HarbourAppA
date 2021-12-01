
package org.solent.com504.project.impl.resource.service;

import java.util.Random;
import java.util.List;
import java.util.Arrays;
import java.util.Random;

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


import org.solent.com504.project.model.order.dao.GPSDAO;
import org.solent.com504.project.model.order.dao.ShipLocationDAO;
import org.solent.com504.project.model.order.dao.OrderDAO;

@Service
public class GPSService {
	private transient final org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog(this.getClass());

        @Autowired
	private GPSDAO GPSDao;

	@Autowired
	private ShipLocationDAO shipLocationDAO;

	@Autowired
	private OrderDAO orderDAO;
        
     
}
        //not sure how GPS service will work yet.

               
                

        