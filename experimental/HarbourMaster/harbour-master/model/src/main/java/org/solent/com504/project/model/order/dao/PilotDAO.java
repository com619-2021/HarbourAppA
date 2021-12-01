
package org.solent.com504.project.model.order.dao;


//import org.solent.com504.project.repository.PilotRepository;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.solent.com504.project.model.dto.Pilot;
import java.util.UUID;

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

	
	public Pilot findByUUID(UUID uuid) {
		return pilotRepository.findOneByUuid(uuid);
	}

	public Boolean deleteByUUID(UUID uuid) {
		if (pilotRepository.findOneByUuid(uuid) != null) {
			pilotRepository.deleteByUuid(uuid);
			return true;
		}
		return false;
	}

	public List<Pilot> getAllPilots() {
		return pilotRepository.findAll();
	}
}