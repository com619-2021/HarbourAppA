package org.solent.com504.project.impl.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.solent.com504.project.model.dto.ShipLocation;

@Repository
public interface ShipLocationRepository extends JpaRepository<ShipLocation, Integer> {
    public long count();
}