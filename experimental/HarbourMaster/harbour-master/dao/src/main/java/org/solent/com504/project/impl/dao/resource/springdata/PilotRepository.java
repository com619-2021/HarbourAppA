
package org.solent.com504.project.impl.dao.resource.springdata;

//may need to move to another prj

import org.solent.com504.project.model.dto.Pilot;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.UUID;


@Repository
public interface PilotRepository extends JpaRepository<Pilot, Integer> {
    
    public Pilot findOneByUuid(@Param("uuid") UUID uuid);

	public List<Pilot> findAll();

	public void deleteByUuid(@Param("uuid") UUID uuid);
    
	@Query(value = "SELECT * FROM pilots WHERE first_name = :firstName AND last_name = :lastName", nativeQuery = true)
	public List<Pilot> findByFullName(@Param("firstName") String firstName, @Param("lastName") String lastName);


}