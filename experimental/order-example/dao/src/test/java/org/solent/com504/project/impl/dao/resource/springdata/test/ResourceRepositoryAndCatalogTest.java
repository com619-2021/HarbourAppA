/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.project.impl.dao.resource.springdata.test;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import org.solent.com504.project.impl.dao.party.springdata.test.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.solent.com504.project.model.party.dto.Party;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.solent.com504.project.impl.dao.party.springdata.PartyRepository;
import org.solent.com504.project.impl.dao.resource.springdata.ResourceCatalogRepository;
import org.solent.com504.project.impl.dao.resource.springdata.ResourceRepository;
import org.solent.com504.project.impl.dao.spring.test.DAOTestConfiguration;
import org.solent.com504.project.model.party.dto.PartyRole;
import org.solent.com504.project.model.resource.dto.Characteristic;
import org.solent.com504.project.model.resource.dto.Resource;
import org.solent.com504.project.model.resource.dto.ResourceAccess;
import org.solent.com504.project.model.resource.dto.ResourceCatalog;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

/**
 *
 * @author cgallen
 */
@RunWith(SpringJUnit4ClassRunner.class)
// ApplicationContext will be loaded from the DAOTestConfiguration class
@ContextConfiguration(classes = DAOTestConfiguration.class, loader = AnnotationConfigContextLoader.class)
public class ResourceRepositoryAndCatalogTest {

    final static Logger LOG = LogManager.getLogger(ResourceRepositoryAndCatalogTest.class);

    @Autowired
    private PartyRepository partyRepository = null;

    @Autowired
    private ResourceRepository resourceRepository = null;

    @Autowired
    private ResourceCatalogRepository resourceCatalogRepository = null;

    @Before
    public void before() {
        LOG.debug("before test running");
        assertNotNull(partyRepository);
        assertNotNull(resourceRepository);
        assertNotNull(resourceCatalogRepository);
        LOG.debug("before test complete");
    }

    @Transactional
    @Test
    public void resourceRepositoryTest() {
        LOG.debug("start of resourceRepositoryTest");

        Party party1 = new Party();
        party1.setFirstName("party1");
        party1.setPartyRole(PartyRole.SELLER);
        party1 = partyRepository.save(party1);
        LOG.debug("party1=" + party1);

        Long id = party1.getId();
        Party party2 = partyRepository.getOne(id);
        LOG.debug("party2=" + party2);

        Resource resource1 = new Resource();
        String uuid1 = UUID.randomUUID().toString();

        resource1.setUuid(uuid1);

        resource1.setResourceOwner(party2);

        resource1.setResourceController(ResourceAccess.INTERNAL);
        String description = "this is a big boat";
        resource1.setDescription(description);
        String resourceTypeName = "org.solent.oodd.port.ship";
        resource1.setResourceTypeName(resourceTypeName);

        // (String name, String value, String description)
        Characteristic char1 = new Characteristic("length", "", "length of boat");
        Characteristic char2 = new Characteristic("tunnage", "", "grose tunnage of boat");

        List<Characteristic> characteristics = Arrays.asList(char1, char2);
        resource1.setCharacteristics(characteristics);

        resource1 = resourceRepository.save(resource1);

        Party resourceOwner = resource1.getResourceOwner();

        LOG.debug("resourceEntry=" + resource1);

        Long id1 = resource1.getId();

        Resource resource2 = resourceRepository.getOne(id1);
        LOG.debug("resourceEntry2=" + resource2);

        List<Resource> resourceList = resourceRepository.findByUuid(uuid1);

        Resource resource3 = resourceList.get(0);
        LOG.debug("resourceEntry3=" + resource2);

        LOG.debug("end of resourceRepositoryTest");
    }

    @Transactional
    @Test
    public void catalogEntryCatalogRepositoryTest() {
        LOG.debug("start of catalogEntryRepositoryTest");
        ResourceCatalog catalogEntry1 = new ResourceCatalog();
        String uuid1 = UUID.randomUUID().toString();
        catalogEntry1.setUuid(uuid1);

        catalogEntry1.setResourceController(ResourceAccess.INTERNAL);
        String description = "this is a big boat";
        catalogEntry1.setDescription(description);
        String catalogEntryTypeName = "org.solent.oodd.port.ship";
        catalogEntry1.setResourceTypeName(catalogEntryTypeName);

        // (String name, String value, String description)
        Characteristic char1 = new Characteristic("length", "", "length of boat");
        Characteristic char2 = new Characteristic("tunnage", "", "grose tunnage of boat");

        List<Characteristic> characteristics = Arrays.asList(char1, char2);
        catalogEntry1.setCharacteristics(characteristics);

        catalogEntry1 = resourceCatalogRepository.save(catalogEntry1);

        Party catalogEntryOwner = catalogEntry1.getResourceOwner();

        LOG.debug("catalogEntryEntry=" + catalogEntry1);

        Long id1 = catalogEntry1.getId();

        ResourceCatalog catalogEntry2 = resourceCatalogRepository.getOne(id1);
        LOG.debug("catalogEntryEntry2=" + catalogEntry2);

        List<ResourceCatalog> catalogList = resourceCatalogRepository.findByUuid(uuid1);

        ResourceCatalog catalog3 = catalogList.get(0);
        LOG.debug("catalog3=" + catalog3);

        LOG.debug("end of catalogEntryRepositoryTest");
    }

}
