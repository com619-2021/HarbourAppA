package repository;


import dto.Order;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
	@Query(value = "SELECT * FROM orders WHERE ID = :id", nativeQuery = true)
	public List<Order> findByID(@Param("ID") int id);


}