package org.solent.com504.project.impl.dao.dao;

import java.util.List;
import java.util.UUID;

import org.solent.com504.project.model.dto.GPS;
import org.solent.com504.project.impl.dao.repository.GPSRepository;
import org.solent.com504.project.model.dto.Ship;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public class GPSDAO {
    
        
	@Autowired
	private GPSRepository gpsRepository;

	public GPS findById(int id) {
		return gpsRepository.findById(id).isPresent() ? gpsRepository.findById(id).get() : null;
	}

	public Boolean existsById(int id) {
		return gpsRepository.existsById(id);
	}

	public GPS save(GPS GPS) {
		return gpsRepository.save(GPS);
	}

	public List<GPS> findAll() {
		return gpsRepository.findAll();
	}

	public void deleteById(int id) {
		gpsRepository.deleteById(id);
	}

	public void delete(GPS GPS) {
		gpsRepository.delete(GPS);
	}

	public void deleteAll() {
		gpsRepository.deleteAll();
	}

	
	
}