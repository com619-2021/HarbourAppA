/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.project.impl.order.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.solent.com504.project.model.dto.ReplyMessage;
import org.solent.com504.project.model.order.dto.Order;
import org.solent.com504.project.model.order.service.UpdateOrdersService;
import org.springframework.stereotype.Service;

/**
 *
 * @author cgallen
 */
@Service
public class UpdateOrdersServiceImpl implements UpdateOrdersService {

    final static Logger LOG = LogManager.getLogger(UpdateOrdersServiceImpl.class);

    @Override
    public ReplyMessage postCreateOrder(Order orderRequest, String changeRequestorUuid, String changeReason) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ReplyMessage postCancelOrder(String orderUuid, String changeRequestorUuid, String changeReason) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ReplyMessage postConfirmOrder(String orderUuid, String changeRequestorUuid, String changeReason) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ReplyMessage postPlaceExternalOrder(String orderUuid, String changeRequestorUuid, String changeReason) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ReplyMessage postAddSubOrder(String parentOrderUuid, String childOrderUuid, String changeRequestorUuid, String changeReason) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ReplyMessage postRemoveSubOrder(String parentOrderUuid, String childOrderUuid, String changeRequestorUuid, String changeReason) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ReplyMessage postOrderChangeRequest(Order changeRequest, String changeRequestorUuid, String changeReason) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ReplyMessage postAcceptOrderChangeRequest(String changeRequestUuid, String responseDescription) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ReplyMessage postRejectOrderChangeRequest(String changeRequestUuid, String responseDescription) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
