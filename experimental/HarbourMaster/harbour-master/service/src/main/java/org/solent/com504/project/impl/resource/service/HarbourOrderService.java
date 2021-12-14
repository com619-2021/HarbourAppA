/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.project.impl.resource.service;

import java.time.LocalDate;
import java.util.ArrayList;
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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.graalvm.compiler.virtual.phases.ea.PartialEscapeBlockState.Final;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



//needs code for placing order, cancelling order
//scheduling pilot??


@Service
public class HarbourOrderService {
    
    //it works
   
    private transient final org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog(this.getClass());
    

	@Autowired
	private OrderDAO orderDAO;

	@Autowired
	private TideDAO tideDAO;

	@Autowired
	private PilotDAO pilotDAO;
        
  
        
}

