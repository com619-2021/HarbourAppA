package org.solent.com504.project.impl.dao.user.springdata;

import java.util.List;
import java.util.Set;
import org.solent.com504.project.model.user.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

    public User findByUsername(String username);

    @Query("select u from User u where u.firstName = :firstName and u.secondName = :secondName")
    public List<User> findByNames(@Param("firstName") String firstName, @Param("secondName") String secondName);
    
}

//
//package org.solent.com504.project.impl.dao.party.springdata;
//
//import java.util.List;
//import org.solent.com504.project.model.party.dao.PilotAvailability;
//import org.solent.com504.project.model.party.dto.Pilot;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//
//@Repository
//public interface PilotRepository {
//    @Query("We need some sort of Join here")
//    public List<Pilot> findByPilotAvailability(@Param("pilotAvailability") PilotAvailability pilotAvailability);
//
//    @Query("We need some sort of Join here")
//    public List<Pilot> findByName(@Param("name") String name);
//
//
//    @Query("We need some sort of Join here")
//    public List<Pilot> findAll();
//}