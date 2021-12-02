package org.solent.com504.project.impl.dao.dao;


import java.util.List;
import java.time.LocalTime;
import java.time.DayOfWeek;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import org.solent.com504.project.model.dto.Tide;
import org.solent.com504.project.impl.dao.repository.TideRepository;


//from grpb- changed to fit our model.


@Repository
public class TideDAO {
	@Autowired
	private TideRepository tideRepository;

	public Tide findById(int id) {
		return tideRepository.findById(id).get();
	}

	public Boolean existsById(int id) {
		return tideRepository.existsById(id);
	}

	public Tide save(Tide tide) {
		return tideRepository.save(tide);
	}

	public List<Tide> findAll() {
		return tideRepository.findAll();
	}

	public void deleteById(int id) {
		tideRepository.deleteById(id);
	}

	public void delete(Tide tide) {
		tideRepository.delete(tide);
	}

	public void deleteAll() {
		tideRepository.deleteAll();
	}

	public Tide getTideAt(DayOfWeek day, LocalTime time) {
		int dayInt = day.getValue() - 1;
		return tideRepository.getTideAt(dayInt, time);
	}

	public LocalTime getNextSafeTide(double draft) {
		return tideRepository.getNextSafeTide(draft);
	}
        
        public List<Tide> getSafeTidesOnDay(DayOfWeek day, double draft) {
		int dayInt = day.getValue() - 1;
		return tideRepository.getSafeTidesOnDay(dayInt, draft);
	}
}

