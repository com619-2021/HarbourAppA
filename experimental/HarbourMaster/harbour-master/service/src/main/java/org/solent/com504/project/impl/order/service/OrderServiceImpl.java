/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.project.impl.order.service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.solent.com504.project.impl.dao.order.springdata.OrderChangeRequestRepository;
import org.solent.com504.project.impl.dao.order.springdata.OrderRepository;
import org.solent.com504.project.impl.dao.party.springdata.PartyRepository;
import org.solent.com504.project.impl.dao.resource.springdata.ResourceCatalogRepository;
import org.solent.com504.project.impl.dao.resource.springdata.ResourceRepository;
import org.solent.com504.project.model.dto.ReplyMessage;
import org.solent.com504.project.model.order.dto.Order;
import org.solent.com504.project.model.order.dto.OrderEntity;
import org.solent.com504.project.model.order.dto.OrderMapper;
import org.solent.com504.project.model.order.service.OrderService;
import org.solent.com504.project.model.party.dto.Party;
import org.solent.com504.project.model.resource.dto.AbstractResourceMapper;
import org.solent.com504.project.model.resource.dto.Resource;
import org.solent.com504.project.model.resource.dto.ResourceAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author cgallen
 */
@Service
public class OrderServiceImpl implements OrderService {

    final static Logger LOG = LogManager.getLogger(OrderServiceImpl.class);

    @Autowired
    private PartyRepository partyRepository = null;

    @Autowired
    private ResourceRepository resourceRepository = null;

    @Autowired
    private ResourceCatalogRepository resourceCatalogRepository = null;

    @Autowired
    private OrderRepository orderRepository = null;

    @Autowired
    private OrderChangeRequestRepository orderChangeRequestRepository = null;

    @Override
    @Transactional
    public ReplyMessage getOrderByUuid(String uuid) {
        ReplyMessage replyMessage = new ReplyMessage();

        List<OrderEntity> orderList = orderRepository.findByUuid(uuid);
        if (orderList.isEmpty()) {
            throw new IllegalArgumentException("cannot find order uuid not found=" + uuid);
        }

        OrderEntity orderEntity = orderList.get(0);

        //create a detached order dto for reply message
        Order detachedOrder = orderEntityToOrder(orderEntity);

        replyMessage.setOrderList(Arrays.asList(detachedOrder));
        replyMessage.setOffset(0);
        replyMessage.setLimit(1);
        replyMessage.setTotalCount(1L);
        return replyMessage;
    }

    @Override
    @Transactional
    public ReplyMessage deleteOrderByUuid(String uuid) {
        List<OrderEntity> orderList = orderRepository.findByUuid(uuid);
        if (orderList.isEmpty()) {
            throw new IllegalArgumentException("cannot delete order uuid not found=" + uuid);
        }
        orderRepository.delete(orderList.get(0));
        return new ReplyMessage();
    }

    @Override
    @Transactional
    public ReplyMessage postCreateOrder(Order order, String ownerPartyUUID) {
        order.setId(null); // may be differnt db id
        List<Party> partyList = partyRepository.findByUuid(ownerPartyUUID);
        if (partyList.isEmpty()) {
            throw new IllegalArgumentException("cannot create order party not found ownerPartyUUID=" + ownerPartyUUID);
        }
        Party resourceOwner = partyList.get(0);
        OrderEntity orderEntity;
        if (ResourceAccess.EXTERNAL.equals(order.getResourceAccess())) {
            orderEntity = new OrderEntity();
            orderEntity.setResourceAccess(ResourceAccess.EXTERNAL);
            orderEntity.setExternalOrder(order);
        } else {
            orderEntity = OrderMapper.INSTANCE.orderToOrderEntity(order);
            orderEntity.setUuid(UUID.randomUUID().toString());
            orderEntity.setId(null); // may be differnt db id
            orderEntity.setResourceAccess(ResourceAccess.INTERNAL);
            
            //TODO try and create resource reference
        }
        orderEntity.setOrderOwner(resourceOwner);

        orderEntity = orderRepository.saveAndFlush(orderEntity);

        //create a detached order dto for reply message
        Order detachedOrder = orderEntityToOrder(orderEntity);

        ReplyMessage replyMessage = new ReplyMessage();
        replyMessage.setOrderList(Arrays.asList(detachedOrder));
        replyMessage.setOffset(0);
        replyMessage.setLimit(1);
        replyMessage.setTotalCount(1L);
        return replyMessage;
    }

    @Override
    public ReplyMessage putUpdateOrder(Order order) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ReplyMessage getOrderByTemplate(Order orderSearchTemplate, Integer offset, Integer limit) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private Order orderEntityToOrder(OrderEntity orderEntity) {
        //create a detached order dto for reply message
        Order order;
        if (ResourceAccess.INTERNAL == orderEntity.getResourceAccess()) {
            order = OrderMapper.INSTANCE.orderEntityToOrder(orderEntity);
        } else { // external
            order = orderEntity.getExternalOrder();
        }
        return order;
    }

}
