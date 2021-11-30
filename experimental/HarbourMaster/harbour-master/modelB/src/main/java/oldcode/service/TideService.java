package service;


import java.time.LocalTime;
import java.time.LocalDate;
import java.time.DayOfWeek;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import dto.Tide;
import dao.TideDAO;


@Service
public class TideService {
	private transient final org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog(this.getClass());

	@Autowired
	private TideDAO tideDAO;

	public Boolean getTideSafety(LocalDate date, LocalTime time, int draft) {
		DayOfWeek day = date.getDayOfWeek();
		return tideDAO.getTideAt(day, time).getHeight() > draft;
	}

	public LocalTime getNextSafeTide(int draft) {
		return tideDAO.getNextSafeTide(draft);
	}
}