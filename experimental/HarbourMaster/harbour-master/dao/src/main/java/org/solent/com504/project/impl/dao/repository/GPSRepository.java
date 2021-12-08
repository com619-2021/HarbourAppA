package org.solent.com504.project.impl.dao.repository;


import org.solent.com504.project.model.dto.GPS;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


@Repository
public interface GPSRepository extends JpaRepository<GPS, Integer> {

    public GPS findByShipID();
	
}
