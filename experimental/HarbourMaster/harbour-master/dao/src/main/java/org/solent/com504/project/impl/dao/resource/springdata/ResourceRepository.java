/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.project.impl.dao.resource.springdata;

import java.util.List;
import org.solent.com504.project.model.party.dto.Party;
import org.solent.com504.project.model.resource.dto.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author cgallen
 */
@Repository
public interface ResourceRepository  extends JpaRepository<Resource, Long>, JpaSpecificationExecutor<Resource> {
    
    @Query("select r from Resource r  where r.uuid = :uuid")
    public List<Resource> findByUuid(@Param("uuid") String uuid);
}
