
package org.solent.com504.project.impl.resource.service;

import org.solent.com504.project.model.dto.Pilot;
import org.solent.com504.project.impl.dao.dao.PilotDAO;



import org.solent.com504.project.impl.dao.dao.TideDAO;

import org.solent.com504.project.model.dto.Pilot;
import org.solent.com504.project.model.dto.Ship;
import org.solent.com504.project.model.dto.Berth;


import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.LocalDate;
import java.time.DayOfWeek;

import java.util.Collections;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
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