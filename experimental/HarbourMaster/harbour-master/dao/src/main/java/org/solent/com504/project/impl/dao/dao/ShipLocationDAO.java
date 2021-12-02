package org.solent.com504.project.impl.dao.dao;

import java.util.List;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.solent.com504.project.model.dto.ShipLocation;
import org.solent.com504.project.impl.dao.repository.ShipLocationRepository;
import org.springframework.stereotype.Component;

@Component
public class ShipLocationDAO {
	@Autowired
	private ShipLocationRepository shipLocationRepository;

	public ShipLocation findById(int id) {
		return shipLocationRepository.findById(id).get();
	}

	public Boolean existsById(int id) {
		return shipLocationRepository.existsById(id);
	}

	public ShipLocation save(ShipLocation ShipLocation) {
		return shipLocationRepository.save(ShipLocation);
	}

	public List<ShipLocation> findAll() {
		return shipLocationRepository.findAll();
	}

	public void deleteById(int id) {
		shipLocationRepository.deleteById(id);
	}

	public void delete(ShipLocation ShipLocation) {
		shipLocationRepository.delete(ShipLocation);
	}

	public void deleteAll() {
		shipLocationRepository.deleteAll();
	}
}

//Followed Group B's code