package org.solent.com504.project.impl.dao.repository;

import org.solent.com504.project.model.dto.Order;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
	public Order findOneByUuid(@Param("uuid") UUID uuid);

	
}

