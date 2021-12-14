
package org.solent.com504.project.impl.dao.repository;

import org.solent.com504.project.model.dto.Order;
import java.util.List;
import java.util.UUID;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

@Repository
public interface HarbourOrderRepository extends JpaRepository<Order, Integer> {
	
//
//  @Query("select o from Orders o LEFT JOIN FETCH o.Orders where o.uuid = :uuid")
//    public List<Order> findByUuid(@Param("uuid") String uuid);

    
}