/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.project.model.resource.dao;


import org.solent.com504.project.model.dto.Order;
//import repository.OrderRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
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

	public List<Order> findByID(int id) {
		return orderRepository.findByID(id);
	}

    public Order findByShipId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}