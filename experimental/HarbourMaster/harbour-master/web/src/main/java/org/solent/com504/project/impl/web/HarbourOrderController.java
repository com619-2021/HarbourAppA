/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.project.impl.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.UUID;
import org.solent.com504.project.model.order.dto.Order;
import org.solent.com504.project.impl.resource.service.HarbourOrderService;
import org.solent.com504.project.model.dto.Ship;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RestController;



@RestController
@Api
public class HarbourOrderController {
	private transient final org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog(this.getClass());

	@Autowired
	HarbourOrderService orderService;
        
        
       
        @GetMapping(value = "/api/test")
	public ResponseEntity<Object> get_test() {
		log.info("/api/test: called.");
		return new ResponseEntity<>("REST is working.", HttpStatus.OK);
	}
        
        
        //does this show on web
          @GetMapping(value= "/api/listorders")
    public String listOrders(Model model) {
        String ordersModelName = "orders";
        //database named orders

        List<org.solent.com504.project.model.dto.Order> orders = orderService.findAll();

        model.addAttribute(ordersModelName, orders);

        return "pages/listOrders";
    }
 

}
        

        //take in ship object or ship UUID?
        //is responseentity supposed to be an object?
        //create isvalid method for pilot/ship
//        
//        
//        @RequestMapping(value = "/api/bookPilot", method = RequestMethod.POST)
//	public ResponseEntity<Object> book_pilot(@RequestBody Ship ship) {
//		log.info("/api/bookPilot: called.");
//                log.info("needs .");
//		     
//            return null;
//        }
 