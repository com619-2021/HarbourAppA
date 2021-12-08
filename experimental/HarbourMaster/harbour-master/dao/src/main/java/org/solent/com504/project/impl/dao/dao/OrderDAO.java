package org.solent.com504.project.impl.dao.dao;



import org.solent.com504.project.model.dto.Order;
import org.solent.com504.project.impl.dao.repository.OrderRepository;

import java.util.List;
import java.util.UUID;
import org.solent.com504.project.model.dto.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


@Component
public class OrderDAO {
	@Autowired
	private OrderRepository orderRepository;

	public Order findById(int id) {
		return orderRepository.findById(id).isPresent() ? orderRepository.findById(id).get() : null;
	}

	public Boolean existsById(int id) {
		return orderRepository.existsById(id);
	}

	public Order save(Order order) {
		return orderRepository.save(order);
	}

	public List<Order> findAll() {
		return orderRepository.findAll();
	}

	public void deleteById(int id) {
		orderRepository.deleteById(id);
	}

	public void delete(Order order) {
		orderRepository.delete(order);
	}

	public void deleteAll() {
		orderRepository.deleteAll();
	}

	
}