
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.project.model.dto.test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;

import org.solent.com504.project.model.party.dto.Party;
import org.solent.com504.project.model.party.dto.Address;
import org.solent.com504.project.model.dto.ReplyMessage;
import org.solent.com504.project.model.order.dto.ChangeStatus;
import org.solent.com504.project.model.order.dto.Order;
import org.solent.com504.project.model.order.dto.OrderChangeRequest;
import org.solent.com504.project.model.order.dto.OrderHref;
import org.solent.com504.project.model.order.dto.OrderMapper;
import org.solent.com504.project.model.order.dto.OrderStatus;
import org.solent.com504.project.model.party.dto.PartyHref;
import org.solent.com504.project.model.party.dto.PartyMapper;
import org.solent.com504.project.model.party.dto.PartyRole;
import org.solent.com504.project.model.party.dto.PartyStatus;
import org.solent.com504.project.model.resource.dto.AbstractResource;
import org.solent.com504.project.model.resource.dto.AbstractResourceMapper;
import org.solent.com504.project.model.resource.dto.Characteristic;
import org.solent.com504.project.model.resource.dto.Resource;
import org.solent.com504.project.model.resource.dto.ResourceHref;
import org.solent.com504.project.model.user.dto.Role;
import org.solent.com504.project.model.user.dto.User;
import org.solent.com504.project.model.user.dto.UserRoles;

public class ModelJaxbTest {

    final static Logger LOG = LogManager.getLogger(ModelJaxbTest.class);

    public JAXBContext jaxbContext;

    @Before
    public void setup() {
        // this contains a list of Jaxb annotated classes for the context to parse, seperated by :
        // NOTE you must also have a jaxb.index or jaxb ObjectFactory in the same classpath
        try {
            jaxbContext = JAXBContext.newInstance(
                    "org.solent.com504.project.model.dto"
                    + ":org.solent.com504.project.model.user.dto"
                    + ":org.solent.com504.project.model.party.dto"
                    + ":org.solent.com504.project.model.resource.dto"
                    + ":org.solent.com504.project.model.order.dto");
        } catch (JAXBException e) {
            throw new RuntimeException("problem creating jaxb context", e);
        }
    }

    @Test
    public void testModelJaxb1() {

        try {

            // test file we will write and read. 
            // Note in target folder so that will be deleted on each run and will not be saved to git
            File file = new File("target/testTransactionData.xml");
            LOG.debug("writing test file to " + file.getAbsolutePath());

            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            ReplyMessage replyMessage = new ReplyMessage();

            List<Party> partyList = new ArrayList<Party>();
            replyMessage.setPartyList(partyList);
            Party party = new Party();
            party.setPartyStatus(PartyStatus.ACTIVE);
            party.setPartyRole(PartyRole.UNDEFINED);
            Address address = new Address();
            address.setAddressLine1("home for me");
            party.setAddress(address);
            partyList.add(party);

            // create XML from the object
            // marshal the object lists to system out, a file and a stringWriter
            jaxbMarshaller.marshal(replyMessage, System.out);
            jaxbMarshaller.marshal(replyMessage, file);

            // string writer is used to compare received object
            StringWriter sw1 = new StringWriter();
            jaxbMarshaller.marshal(replyMessage, sw1);

            // having written the file we now read in the file for test
            Unmarshaller jaxbUnMarshaller = jaxbContext.createUnmarshaller();
            ReplyMessage receivedTransactionResult = (ReplyMessage) jaxbUnMarshaller.unmarshal(file);

            StringWriter sw2 = new StringWriter();
            jaxbMarshaller.marshal(receivedTransactionResult, sw2);

            // check transmitted and recieved messages are the same
            assertEquals(sw1.toString(), sw2.toString());

        } catch (JAXBException e) {
            throw new RuntimeException("problem testing jaxb marshalling", e);
        }
    }

    @Test
    public void testUserModelJaxb() {
        try {
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // create objects to marshal
            User user = new User();
            Role role1 = new Role(UserRoles.ROLE_USER);
            Role role2 = new Role(UserRoles.ROLE_GLOBAL_ADMIN);
            user.setFirstName("firstName");
            user.setSecondName("secondName");
            user.setPassword("password");
            user.setPasswordConfirm("password");
            Set<Role> roles = new HashSet(Arrays.asList(role1, role2));
            user.setRoles(roles);

            // string writer is used to compare received object
            StringWriter sw1 = new StringWriter();
            jaxbMarshaller.marshal(user, sw1);

            LOG.debug("marshaled code" + sw1);

            // having written the file we now read in the file for test
            Unmarshaller jaxbUnMarshaller = jaxbContext.createUnmarshaller();
            InputStream stream = new ByteArrayInputStream(sw1.toString().getBytes(StandardCharsets.UTF_8));

            User receiveduser = (User) jaxbUnMarshaller.unmarshal(stream);
            LOG.debug("receiveduser=" + receiveduser);

        } catch (JAXBException e) {
            throw new RuntimeException("problem testing jaxb marshalling", e);
        }
    }

    @Test
    public void testResourceModelJaxb() {
        try {
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // create objects to marshal
            ReplyMessage replyMessage = new ReplyMessage();

            Resource resource = new Resource();
            List<Characteristic> characteristics = new ArrayList<Characteristic>();
            Characteristic characteristic1 = new Characteristic("nameIsName", "valueIsValue", "description is descriptoion");
            characteristics.add(characteristic1);

            resource.setCharacteristics(characteristics);
            resource.setDescription("the description");
            resource.setUuid(UUID.randomUUID().toString());
            resource.setHref("http://localhost/resource/" + resource.getUuid());
            resource.setId(1L);
            resource.setName("testResource");

            Party party = new Party();
            party.setUuid(UUID.randomUUID().toString());
            party.setHref("http://localhost/party/" + party.getUuid());
            party.setPartyStatus(PartyStatus.ACTIVE);
            party.setPartyRole(PartyRole.UNDEFINED);
            Address address = new Address();
            address.setAddressLine1("home for me");
            party.setAddress(address);

            resource.setResourceOwner(party);

            resource.setResourceTypeName("org.solent.ship");

            List<Resource> resourceList = new ArrayList<Resource>();
            resourceList.add(resource);
            replyMessage.setResourceList(resourceList);

            // string writer is used to compare received object
            StringWriter sw1 = new StringWriter();
            jaxbMarshaller.marshal(replyMessage, sw1);

            LOG.debug("TestResource marshaled code" + sw1);

            // having written the file we now read in the file for test
            Unmarshaller jaxbUnMarshaller = jaxbContext.createUnmarshaller();
            InputStream stream = new ByteArrayInputStream(sw1.toString().getBytes(StandardCharsets.UTF_8));

            ReplyMessage receivedReplyMessage = (ReplyMessage) jaxbUnMarshaller.unmarshal(stream);
            LOG.debug("TestResource receivedReplyMessage=" + receivedReplyMessage);

        } catch (JAXBException e) {
            throw new RuntimeException("problem testing jaxb marshalling", e);
        }
    }

    @Test
    public void testOrderModelJaxb() {
        try {
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // create objects to marshal
            ReplyMessage replyMessage = new ReplyMessage();

            Order order = new Order();
            order.setDescription("order description");
            order.setName("PO1234");
            order.setEndDate(new Date());
            order.setOrderDate(new Date());

            order.setStartDate(new Date());
            order.setStatus(OrderStatus.ACKNOWLEGED);
            order.setUuid(UUID.randomUUID().toString());
            order.setHref("http://orderhref/" + order.getUuid());
            
                        Resource resource = new Resource();
            List<Characteristic> characteristics = new ArrayList<Characteristic>();
            Characteristic characteristic1 = new Characteristic("nameIsName", "valueIsValue", "description is descriptoion");
            characteristics.add(characteristic1);

            resource.setCharacteristics(characteristics);
            resource.setDescription("the description");
            resource.setUuid(UUID.randomUUID().toString());
            resource.setHref("http://localhost/resource/" + resource.getUuid());
            resource.setId(1L);
            resource.setName("testResource");
            
            List<AbstractResource> resourceList = Arrays.asList(resource);
            List<ResourceHref> resourceOrService = AbstractResourceMapper.INSTANCE.abstractResourceListToResourceHrefList(resourceList)
            ;
            order.setResourceOrService(resourceOrService);

            Party orderOwner = new Party();
            orderOwner.setUuid(UUID.randomUUID().toString());
            orderOwner.setHref("http://localhost/party/" + orderOwner.getUuid());
            orderOwner.setPartyStatus(PartyStatus.ACTIVE);
            orderOwner.setPartyRole(PartyRole.UNDEFINED);
            orderOwner.setFirstName("company x");
            Address address = new Address();
            address.setAddressLine1("home for me");
            orderOwner.setAddress(address);

            PartyHref orderOwnerHref = PartyMapper.INSTANCE.partyToHref(orderOwner);

            order.setOrderOwner(orderOwnerHref);

            Order subOrder = new Order();
            subOrder.setUuid(UUID.randomUUID().toString());
            subOrder.setHref("http://orderhref/" + subOrder.getUuid());

            OrderHref subOrderHref = OrderMapper.INSTANCE.orderToOrderHref(order);
            order.setSubOrders(Arrays.asList(subOrderHref));

            Order parentOrder = new Order();
            parentOrder.setUuid(UUID.randomUUID().toString());
            parentOrder.setHref("http://orderhref/" + parentOrder.getUuid());

            OrderHref parentOrderHref = OrderMapper.INSTANCE.orderToOrderHref(parentOrder);
            order.setParentOrder(parentOrderHref);

            List<Order> orderList = new ArrayList<Order>();
            orderList.add(order);
            replyMessage.setOrderList(orderList);

            OrderChangeRequest orderChangeRequest = new OrderChangeRequest();
            orderChangeRequest.setApprovedDate(new Date());
            orderChangeRequest.setChangeReason("a great change");
            orderChangeRequest.setChangeRequest(order);
            orderChangeRequest.setChangeRequestor(orderOwner);
            orderChangeRequest.setStatus(ChangeStatus.REJECTED);
            orderChangeRequest.setResponseDescription("wasnt a good idea");
            orderChangeRequest.setUuid(UUID.randomUUID().toString());
            orderChangeRequest.setHref("http://orderchangeref/" + orderChangeRequest.getUuid());
            
            orderChangeRequest.setChangeRequest(order);

            List<OrderChangeRequest> orderChangeRequestList = Arrays.asList(orderChangeRequest);

            // also create orderChangeRequest
            replyMessage.setOrderChangeRequestList(orderChangeRequestList);

            // string writer is used to compare received object
            StringWriter sw1 = new StringWriter();
            jaxbMarshaller.marshal(replyMessage, sw1);

            LOG.debug("TestOrder marshaled code" + sw1);

            // having written the file we now read in the file for test
            Unmarshaller jaxbUnMarshaller = jaxbContext.createUnmarshaller();
            InputStream stream = new ByteArrayInputStream(sw1.toString().getBytes(StandardCharsets.UTF_8));

            ReplyMessage receivedReplyMessage = (ReplyMessage) jaxbUnMarshaller.unmarshal(stream);
            LOG.debug("TestOrder receivedReplyMessage=" + receivedReplyMessage);

        } catch (JAXBException e) {
            throw new RuntimeException("problem testing jaxb marshalling", e);
        }
    }

}
