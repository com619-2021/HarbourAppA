package org.solent.com504.project.impl.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalTime;
import java.time.DayOfWeek;
import java.util.List;

import org.solent.com504.project.model.dto.Tide;


@Repository
public interface TideRepository extends JpaRepository<Tide, Integer> {
	@Query(value = "SELECT * FROM tides WHERE day = :day AND :time BETWEEN start AND end", nativeQuery = true)
	public Tide getTideAt(@Param("day") int day, @Param("time") LocalTime time);

	@Query(value = "SELECT * FROM tides WHERE height > :draft AND day = :day", nativeQuery = true)
	public List<Tide> getSafeTidesOnDay(@Param("day") int day, @Param("draft") double draft);


        @Query(value = "SELECT * FROM tides WHERE height > :draft", nativeQuery = true)
          public LocalTime getNextSafeTide(double draft);
}