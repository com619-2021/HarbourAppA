package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import dto.GPS;
import java.util.UUID;

@Repository
public interface GPSRepository extends JpaRepository<GPS, Integer> {
	public GPS findOneByShipUuid(@Param("shipUuid") UUID uuid);
}
