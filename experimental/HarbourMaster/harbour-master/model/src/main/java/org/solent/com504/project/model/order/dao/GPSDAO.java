/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.project.model.order.dao;


import java.util.List;
import java.util.UUID;

import org.solent.com504.project.model.dto.GPS;
import org.solent.com504.project.repository.GPSRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GPSDAO {
	@Autowired
	private GPSRepository GPSRepository;

	public GPS findById(int id) {
		return GPSRepository.findById(id).isPresent() ? GPSRepository.findById(id).get() : null;
	}

	public Boolean existsById(int id) {
		return GPSRepository.existsById(id);
	}

	public GPS save(GPS GPS) {
		return GPSRepository.save(GPS);
	}

	public List<GPS> findAll() {
		return GPSRepository.findAll();
	}

	public void deleteById(int id) {
		GPSRepository.deleteById(id);
	}

	public void delete(GPS GPS) {
		GPSRepository.delete(GPS);
	}

	public void deleteAll() {
		GPSRepository.deleteAll();
	}

	public GPS findByShipUUID(UUID uuid) {
		return GPSRepository.findOneByShipUuid(uuid);
	}

    public GPS findByShipId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
        
}