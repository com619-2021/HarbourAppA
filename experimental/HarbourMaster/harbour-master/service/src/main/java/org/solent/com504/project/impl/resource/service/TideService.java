/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.project.impl.resource.service;


import java.time.LocalTime;
import java.time.LocalDate;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import org.solent.com504.project.model.dto.Tide;
import org.solent.com504.project.impl.dao.dao.TideDAO;


@Service
public class TideService {
	private transient final org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog(this.getClass());

	@Autowired
	private TideDAO tideDAO;
        
         
        
        //using grb b service to test.

public List<Tide> getSafeTidesOnDay(LocalDate date, double draft) {
		return tideDAO.getSafeTidesOnDay(date.getDayOfWeek(), draft);
	}

	public Tide getTideAt(LocalDateTime time) {
		return tideDAO.getTideAt(time.getDayOfWeek(), time.toLocalTime());
	}

	public Boolean getTideSafety(LocalDate date, LocalTime time, int draft) {
		DayOfWeek day = date.getDayOfWeek();
		return tideDAO.getTideAt(day, time).getHeight() > draft;
	}

	public LocalTime getNextSafeTide(int draft) {
		return tideDAO.getNextSafeTide(draft);
	}
}
