/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.project.model.dto.test;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.solent.com504.project.model.order.dto.Order;
import org.solent.com504.project.model.order.dto.OrderHref;
import org.solent.com504.project.model.order.dto.OrderStatus;
import org.solent.com504.project.model.utilities.PrintOutJson;

/**
 *
 * @author cgallen
 */
public class PrintOutJsonTest {

    final static Logger LOG = LogManager.getLogger(PrintOutJsonTest.class);

    @Test
    public void test() {
        Order order = new Order();
        order.setDescription("order description");
        order.setName("PO1234");
        order.setEndDate(new Date());
        order.setOrderDate(new Date());

        order.setStartDate(new Date());
        order.setStatus(OrderStatus.ACKNOWLEGED);
        order.setUuid(UUID.randomUUID().toString());
        order.setHref("http://orderhref/" + order.getUuid());
        
        OrderHref parentOrderHref = new OrderHref();
        parentOrderHref.setHref("http:xxx");
        parentOrderHref.setId(Long.MIN_VALUE);
        parentOrderHref.setName("parent order");
        parentOrderHref.setUuid(UUID.randomUUID().toString());
        
        order.setParentOrder(parentOrderHref);
        
        
        List<OrderHref> subOrders = Arrays.asList(parentOrderHref, parentOrderHref);
        
        
        order.setSubOrders(subOrders);
        
        String jsonOut = PrintOutJson.getJson(order);

        LOG.debug("converted Json " + jsonOut);
    }
}
