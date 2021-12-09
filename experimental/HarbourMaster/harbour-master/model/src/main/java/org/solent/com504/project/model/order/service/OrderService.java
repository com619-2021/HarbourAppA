package org.solent.com504.project.model.order.service;

import java.util.List;
import java.util.UUID;
import org.solent.com504.project.model.dto.ReplyMessage;
import org.solent.com504.project.model.order.dto.Order;

public interface OrderService {

    public ReplyMessage getOrderByUuid(String uuid);

    public ReplyMessage deleteOrderByUuid(String uuid);

    public ReplyMessage postCreateOrder(Order order, String ownerPartyUUID);

    public ReplyMessage putUpdateOrder(Order order);

    public ReplyMessage getOrderByTemplate(Order orderSearchTemplate, Integer offset, Integer limit);

    public boolean cancelOrder(UUID uuid, String reason);

    public Order retrieveOrder(UUID uuid);

 
}
