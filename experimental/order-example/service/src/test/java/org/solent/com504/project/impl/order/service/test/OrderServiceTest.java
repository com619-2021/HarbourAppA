/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.project.impl.order.service.test;

import org.solent.com504.project.impl.resource.service.test.*;
import java.util.ArrayList;
import java.util.Date;
import org.solent.com504.project.impl.service.test.*;
import java.util.List;
import java.util.UUID;
import javax.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.solent.com504.project.model.dto.ReplyMessage;
import org.solent.com504.project.model.order.dto.Order;
import org.solent.com504.project.model.order.dto.OrderStatus;
import org.solent.com504.project.model.order.service.OrderService;
import org.solent.com504.project.model.party.dto.Party;
import org.solent.com504.project.model.party.dto.PartyRole;
import org.solent.com504.project.model.party.service.PartyService;
import org.solent.com504.project.model.resource.dto.Characteristic;
import org.solent.com504.project.model.resource.dto.Resource;
import org.solent.com504.project.model.resource.dto.ResourceAccess;
import org.solent.com504.project.model.resource.service.ResourceCatalogService;
import org.solent.com504.project.model.resource.service.ResourceInventoryService;
import org.solent.com504.project.model.service.ServiceFacade;
import org.solent.com504.project.model.utilities.PrintOutJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

/**
 *
 * @author gallenc
 */
@RunWith(SpringJUnit4ClassRunner.class)
// ApplicationContext will be loaded from the OrderServiceConfig class
@ContextConfiguration(classes = ServiceTestConfiguration.class, loader = AnnotationConfigContextLoader.class)
@Transactional
public class OrderServiceTest {

    final static Logger LOG = LogManager.getLogger(OrderServiceTest.class);

    @Autowired
    ServiceFacade serviceFacade = null;

    @Autowired
    ResourceCatalogService resourceCatalogService = null;

    @Autowired
    ResourceInventoryService resourceInventoryService = null;

    @Autowired
    PartyService partyService = null;

    @Autowired
    OrderService orderService = null;

    public void testFactory() {
        LOG.debug("start OrderServiceTest testFactory");
        assertNotNull(serviceFacade);
        assertNotNull(resourceInventoryService);
        assertNotNull(partyService);
        assertNotNull(orderService);
        LOG.debug("end OrderServiceTest testFactory");
    }

    @Test
    public void internalOrderTest() {
        // create a resource with a party
        Party party1 = new Party();
        party1.setFirstName("party1");
        party1.setPartyRole(PartyRole.SELLER);
        party1 = partyService.save(party1);
        LOG.debug("party1=" + party1);

        Resource resourceIn = mockResource();
        String ownerPartyUUID = party1.getUuid();

        ReplyMessage replyMessage = resourceInventoryService.postCreateResource(resourceIn, ownerPartyUUID);
        List<Resource> resourceList = replyMessage.getResourceList();
        assertEquals(1, resourceList.size());

        Resource createdResource = resourceList.get(0);
        LOG.debug("created Resource:" + createdResource);

        Order order = new Order();
        order.setDescription("order description");
        order.setName("PO1234");
        order.setEndDate(new Date());
        order.setOrderDate(new Date());

        order.setStartDate(new Date());
        order.setStatus(OrderStatus.ACKNOWLEGED);
        String testUUID = UUID.randomUUID().toString();
        order.setUuid(testUUID);
        order.setHref("http://orderhref/" + order.getUuid());

        replyMessage = orderService.postCreateOrder(order, party1.getUuid());

        String jsonOut = PrintOutJson.getJson(replyMessage);

        LOG.debug("reply message " + jsonOut);

    }

    /*
    TOTO REMOVE WHEN NOT NEEDED
     */
    private Resource mockResource() {
        Resource resource = new Resource();
        List<Characteristic> characteristics = new ArrayList();
        characteristics.add(new Characteristic("characteristicName1", "characteristic value1", "characteristic description1"));
        characteristics.add(new Characteristic("characteristicName2", "characteristic value2", "characteristic description2"));
        resource.setCharacteristics(characteristics);
        String description = "temp description";
        resource.setDescription(description);
        String href = "http://temp";
        resource.setHref(href);
        //  resource.setId(1L);
        String name = "resource name";
        resource.setName(name);
        resource.setResourceController(ResourceAccess.EXTERNAL);
        String resourceTypeName = "recource type name";
        resource.setResourceTypeName(resourceTypeName);
        resource.setUuid(UUID.randomUUID().toString());
        return resource;
    }
}
