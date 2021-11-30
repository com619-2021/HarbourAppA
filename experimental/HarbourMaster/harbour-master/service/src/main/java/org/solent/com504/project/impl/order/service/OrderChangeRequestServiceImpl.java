/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.project.impl.order.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.solent.com504.project.impl.resource.service.ResourceCatalogServiceImpl;
import org.solent.com504.project.model.dto.ReplyMessage;
import org.solent.com504.project.model.order.dto.Order;
import org.solent.com504.project.model.order.dto.OrderChangeRequest;
import org.solent.com504.project.model.order.service.OrderChangeRequestService;
import org.springframework.stereotype.Service;

/**
 *
 * @author cgallen
 */
@Service
public class OrderChangeRequestServiceImpl implements OrderChangeRequestService {

    final static Logger LOG = LogManager.getLogger(OrderChangeRequestServiceImpl.class);

    @Override
    public ReplyMessage getOrderChangeRequestByUuid(String uuid) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ReplyMessage deleteOrderChangeRequestByUuid(String uuid) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ReplyMessage postCreateOrderChangeRequest(Order order, String ownerPartyUUID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ReplyMessage putUpdateOrderChangeRequest(Order order) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ReplyMessage getOrderChangeRequestByTemplate(Order orderSearchTemplate, Integer offset, Integer limit) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
