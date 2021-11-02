/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.project.impl.dao.resource.springdata;

import java.util.List;
import org.solent.com504.project.model.resource.dto.Resource;
import org.solent.com504.project.model.resource.dto.ResourceCatalog;
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
public interface ResourceCatalogRepository extends JpaRepository<ResourceCatalog, Long>, JpaSpecificationExecutor<ResourceCatalog> {

    @Query("select c from ResourceCatalog c  where c.uuid = :uuid")
    public List<ResourceCatalog> findByUuid(@Param("uuid") String uuid);

}
