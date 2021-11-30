/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.project.model.dto.test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.mapstruct.MappingTarget;
import org.solent.com504.project.model.party.dto.Address;
import org.solent.com504.project.model.party.dto.Party;
import org.solent.com504.project.model.party.dto.PartyRole;
import org.solent.com504.project.model.party.dto.PartyStatus;
import org.solent.com504.project.model.resource.dto.AbstractResource;
import org.solent.com504.project.model.resource.dto.AbstractResourceMapper;
import org.solent.com504.project.model.resource.dto.Characteristic;
import org.solent.com504.project.model.resource.dto.Resource;
import org.solent.com504.project.model.resource.dto.ResourceCatalog;

/**
 *
 * @author cgallen
 */
public class MapperTest {

    final static Logger LOG = LogManager.getLogger(MapperTest.class);

    public Resource createTestResource() {
        Resource resource = new Resource();
        List<Characteristic> characteristics = new ArrayList<Characteristic>();
        Characteristic characteristic1 = new Characteristic("nameIsName", "valueIsValue", "description is descriptoion");
        characteristics.add(characteristic1);

        resource.setCharacteristics(characteristics);
        resource.setDescription("the description");
        resource.setHref("http://localhost/resource");
        resource.setId(1L);
        resource.setName("testResource");

        Party party = new Party();
        party.setPartyStatus(PartyStatus.ACTIVE);
        party.setPartyRole(PartyRole.UNDEFINED);
        Address address = new Address();
        address.setAddressLine1("home for me");
        party.setAddress(address);
        party.setHref("http://localhost/party");

        resource.setResourceOwner(party);

        resource.setResourceTypeName("org.solent.ship");

        resource.setUuid(UUID.randomUUID().toString());

        return resource;
    }

    @Test
    public void resourceToResourceTest() {
        LOG.debug("abstractResourceToResourceTest START");

        // create resource
        Resource resourceIn = createTestResource();
        LOG.debug("abstractResourceToResourceTest: resourceIn=" + resourceIn);

        // convert resoure to catalog
        ResourceCatalog resourceCatalogOut = AbstractResourceMapper.INSTANCE.abstractResourceToResourceCatalog(resourceIn);
        LOG.debug("abstractResourceToResourceTest: resourceCatalogOut=" + resourceCatalogOut);

        // convert catalog to resource
        Resource resourceOut = AbstractResourceMapper.INSTANCE.abstractResourceToResource(resourceCatalogOut);
        LOG.debug("abstractResourceToResourceTest: resourceOut=" + resourceOut);

        // populate resource from resource
        Resource resourceOut2 = new Resource();

        Resource resourceOutPopulated = AbstractResourceMapper.INSTANCE.updateResource(resourceOut, resourceOut2);
        LOG.debug("abstractResourceToResourceTest: resourceOutPopulated=" + resourceOutPopulated);

    }

}
