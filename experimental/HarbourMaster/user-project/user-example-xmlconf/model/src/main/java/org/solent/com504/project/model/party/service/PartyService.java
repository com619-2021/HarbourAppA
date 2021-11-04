/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.project.model.party.service;

import java.util.List;
import org.solent.com504.project.model.party.dto.Party;
import org.solent.com504.project.model.party.dto.PartyRole;

/**
 *
 * @author cgallen
 */
public interface PartyService {

    public Party findById(Long id);

    public Party save(Party party);

    public List<Party> findAll();

    public void deleteById(long id);

    public void delete(Party party);

    public void deleteAll();

    public List<Party> findByPartyRole(PartyRole partyRole);

    public List<Party> findByName(String firstName, String secondName);
    
    public Party findByUuid(String uuid);
    
    public List<PartyRole> getAvailablePartyRoles();
    
}
//
//pilot service

//import java.util.List;
//import org.solent.com504.project.model.party.dao.PilotAvailability;
//import org.solent.com504.project.model.party.dto.Pilot;
//
//public interface PilotService {
//        public Pilot findById(Long id);
//
//    public Pilot save(Pilot pilot);
//
//    public List<Pilot> findAll();
//
//    public void deleteById(long id);
//
//    public void delete(Pilot pilot);
//
//    public void deleteAll();
//
//    public List<Pilot> findByPilotAvailability(PilotAvailability pilotAvailability);
//
//    public List<Pilot> findByName(String name);
//
//    public List<PilotAvailability> getAvailablePilots();
//}