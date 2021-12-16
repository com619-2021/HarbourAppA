
package org.solent.com504.project.impl.resource.service;

import org.solent.com504.project.model.dto.Pilot;
import org.solent.com504.project.impl.dao.dao.PilotDAO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;


import org.solent.com504.project.impl.dao.dao.TideDAO;

import org.solent.com504.project.model.dto.Pilot;
import org.solent.com504.project.model.dto.Ship;
import org.solent.com504.project.model.dto.Berth;
import java.util.List;
import java.util.Set;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;


import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.LocalDate;
import java.time.DayOfWeek;

import java.util.Collections;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import javax.annotation.PostConstruct;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;



@Service
@Component
@Path("/pilotService")
public class PilotService {
	private transient final org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog(this.getClass());

	@Autowired
	private PilotDAO pilotDAO;

	@Autowired
	private TideDAO tideDAO;

	@Autowired
	private HarbourOrderService orderService;

	@Autowired
	private GPSService gpsService;

	public Boolean createNewPilot(Pilot pilot) {
		pilotDAO.save(pilot);
		return true;
	}

	public Pilot findPilot(UUID uuid) {
		Pilot pilot = pilotDAO.findByUUID(uuid);
		return pilot;
	}
	public List<Pilot> getAllPilots() {
		return pilotDAO.findAll();
	}

	public Boolean deletePilot(UUID uuid) {
		return pilotDAO.deleteByUUID(uuid);
	}

	public void deleteAllPilots() {
		pilotDAO.deleteAll();
	}
        
        
      
        
        
}
       
    
        
        
        
