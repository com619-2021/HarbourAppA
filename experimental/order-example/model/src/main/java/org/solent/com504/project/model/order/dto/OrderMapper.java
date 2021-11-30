/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.project.model.order.dto;

import org.solent.com504.project.model.resource.dto.*;
import org.solent.com504.project.model.party.dto.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

/**
 * maps one abstract resource into another abstract resource can be used to map values of resource entity to dto objects
 *
 * @author cgallen
 */
@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    OrderEntity orderToOrderEntity(Order order);

    Order orderEntityToOrder(OrderEntity orderEntity);

    OrderHref orderToOrderHref(Order order);

    OrderHref orderEntityToOrderHref(OrderEntity orderEntity);

    OrderEntity updateOrderEntity(Order order, @MappingTarget OrderEntity orderEntity);

    @Mapping(source = "firstName", target = "name")
    PartyHref partyToHref(Party partyEntity);

}
