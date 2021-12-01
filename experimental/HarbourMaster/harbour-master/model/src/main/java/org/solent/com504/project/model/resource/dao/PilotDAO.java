
package org.solent.com504.project.model.resource.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.solent.com504.project.model.dto.Pilot;


/**
 *
 * @author jake_
 */

@Repository
public class PilotDAO {
    
    @Autowired
	private PilotRepository pilotRepository;

    public Pilot findById(int id) {
		return pilotRepository.findById(id).isPresent() ? pilotRepository.findById(id).get() : null;
	}

	public Boolean existsById(int id) {
		return pilotRepository.existsById(id);
	}

	public Pilot save(Pilot pilot) {
		return pilotRepository.save(pilot);
	}

	public List<Pilot> findAll() {
		return pilotRepository.findAll();
	}

	public void deleteById(int id) {
		pilotRepository.deleteById(id);
	}

	public void delete(Pilot pilot) {
		pilotRepository.delete(pilot);
	}

	public void deleteAll() {
		pilotRepository.deleteAll();
	}

	public List<Pilot> findByFullName(String firstName, String lastName) {
		return pilotRepository.findByFullName(firstName, lastName);
	}

 
}
