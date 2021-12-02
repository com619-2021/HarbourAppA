package org.solent.com504.project.impl.dao.dao;

import java.util.List;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.solent.com504.project.model.dto.ShipLocation;
import org.solent.com504.project.impl.dao.repository.ShipLocationRepository;

@Repository
public class ShipLocationDAO {
	@Autowired
	private ShipLocationRepository ShipLocationRepository;

	public ShipLocation findById(int id) {
		return ShipLocationRepository.findById(id).get();
	}

	public Boolean existsById(int id) {
		return ShipLocationRepository.existsById(id);
	}

	public ShipLocation save(ShipLocation ShipLocation) {
		return ShipLocationRepository.save(ShipLocation);
	}

	public List<ShipLocation> findAll() {
		return ShipLocationRepository.findAll();
	}

	public void deleteById(int id) {
		ShipLocationRepository.deleteById(id);
	}

	public void delete(ShipLocation ShipLocation) {
		ShipLocationRepository.delete(ShipLocation);
	}

	public void deleteAll() {
		ShipLocationRepository.deleteAll();
	}
}

//Followed Group B's code