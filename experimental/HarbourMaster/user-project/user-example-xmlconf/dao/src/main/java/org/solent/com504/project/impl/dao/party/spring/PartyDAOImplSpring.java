/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.project.impl.dao.party.spring;

import java.util.List;
import org.solent.com504.project.model.party.dto.Party;
import org.solent.com504.project.model.party.dto.PartyRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.solent.com504.project.model.party.dao.PartyDAO;
import org.solent.com504.project.impl.dao.party.springdata.PartyRepository;


/**
 *
 * @author gallenc
 */
@Component
public class PartyDAOImplSpring implements PartyDAO {
    
    @Autowired
    private PartyRepository partyRepository = null;

    @Override
    public Party findById(Long id) {
        return partyRepository.getOne(id);
    }

    @Override
    public Party save(Party party) {
        return partyRepository.save(party);
    }

    @Override
    public List<Party> findAll() {
        return partyRepository.findAll();
    }

    @Override
    public void deleteById(long id) {
        partyRepository.deleteById(id);
    }

    @Override
    public void delete(Party party) {
        partyRepository.delete(party);
    }

    @Override
    public void deleteAll() {
        partyRepository.deleteAll();
    }

    @Override
    public List<Party> findByPartyRole(PartyRole partyRole) {
        return partyRepository.findByPartyRole(partyRole);
    }

    @Override
    public List<Party> findByName(String firstName, String secondName) {
        return partyRepository.findByName(firstName, secondName);
    }

    @Override
    public Party findByUuid(String uuid) {
        List<Party> parties = partyRepository.findByUuid(uuid);
        if (parties.isEmpty()) return null;
        return parties.get(0);
    }
    
    
}

//
//package org.solent.com504.project.impl.dao.party.spring;
//
//import java.util.List;
//import org.solent.com504.project.model.party.dao.PilotAvailability;
//import org.solent.com504.project.model.party.dao.PilotDAO;
//import org.solent.com504.project.model.party.dto.Pilot;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//@Component
//public class PilotDAOImplSpring implements PilotDAO {
//    @Autowired
//    private PilotRepository pilotRepository = null;
//
//    @Override
//    public Pilot findById(Long id) {
//        return pilotRepository.getOne(id);
//    }
//
//    @Override
//    public Pilot save(Pilot pilot) {
//        return pilotRepository.save(pilot);
//    }
//
//    @Override
//    public List<Pilot> findAll() {
//        return pilotRepository.findAll();
//    }
//
//    @Override
//    public void deleteById(long id) {
//        pilotRepository.deleteById(id);
//    }
//
//    @Override
//    public void delete(Pilot pilot) {
//        pilotRepository.delete(pilot);
//    }
//
//    @Override
//    public void deleteAll() {
//        pilotRepository.deleteAll();
//    }
//
//    @Override
//    public List<Pilot> findByPartyRole(PilotAvailability pilotAvailability ) {
//        return pilotRepository.findByPartyRole(pilotAvailability);
//    }
//
//    @Override
//    public List<Pilot> findByName(String name) {
//        return pilotRepository.findByName(name);
//    }
//
//    @Override
//    public List<Pilot> findByPilotAvailability(PilotAvailability pilotAvailability) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//
//}
