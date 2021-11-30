package org.solent.com504.project.model.order.service;

import org.solent.com504.project.model.dto.ReplyMessage;
import org.solent.com504.project.model.order.dto.Order;

public interface OrderChangeRequestService {

    public ReplyMessage getOrderChangeRequestByUuid(String uuid);

    public ReplyMessage deleteOrderChangeRequestByUuid(String uuid);

    public ReplyMessage postCreateOrderChangeRequest(Order order, String ownerPartyUUID);

    public ReplyMessage putUpdateOrderChangeRequest(Order order);

    public ReplyMessage getOrderChangeRequestByTemplate(Order orderSearchTemplate, Integer offset, Integer limit);
}
