/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.project.impl.resource.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.UUID;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.solent.com504.project.impl.dao.party.springdata.PartyRepository;
import org.solent.com504.project.impl.dao.resource.springdata.ResourceCatalogRepository;
import org.solent.com504.project.impl.dao.resource.springdata.ResourceRepository;
import org.solent.com504.project.model.dto.ReplyMessage;
import org.solent.com504.project.model.party.dto.Party;
import org.solent.com504.project.model.resource.dto.AbstractResourceMapper;
import org.solent.com504.project.model.resource.dto.Characteristic;
import org.solent.com504.project.model.resource.dto.Resource;
import org.solent.com504.project.model.resource.dto.ResourceAccess;
import org.solent.com504.project.model.resource.dto.ResourceCatalog;
import org.solent.com504.project.model.resource.service.ResourceInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author cgallen
 */
@Service
public class ResourceInventoryServiceImpl implements ResourceInventoryService {

    final static Logger LOG = LogManager.getLogger(ResourceInventoryServiceImpl.class);

    @Autowired
    private PartyRepository partyRepository = null;

    @Autowired
    private ResourceRepository resourceRepository = null;

    @Autowired
    private ResourceCatalogRepository resourceCatalogRepository = null;

    @Override
    @Transactional
    public ReplyMessage getResourceByuuid(String uuid) {
        ReplyMessage replyMessage = new ReplyMessage();

        List<Resource> resourceList = resourceRepository.findByUuid(uuid);
        if (resourceList.isEmpty()) {
            throw new IllegalArgumentException("cannot find resource uuid not found=" + uuid);
        }
        Resource resourceEntity = resourceList.get(0);

        //create a detached resource dto for reply message
        Resource detachedResource = AbstractResourceMapper.INSTANCE.abstractResourceToResource(resourceEntity);
        replyMessage.setResourceList(Arrays.asList(detachedResource));
        replyMessage.setOffset(0);
        replyMessage.setLimit(1);
        replyMessage.setTotalCount(1L);
        return replyMessage;
    }

    @Override
    @Transactional
    public ReplyMessage deleteResourceByUuid(String uuid) {
        List<Resource> resourceList = resourceRepository.findByUuid(uuid);
        if (resourceList.isEmpty()) {
            throw new IllegalArgumentException("cannot delete resource uuid not found=" + uuid);
        }
        resourceRepository.delete(resourceList.get(0));
        return new ReplyMessage();
    }

    @Override
    @Transactional
    public ReplyMessage postCreateResource(Resource resource, String ownerPartyUUID) {
        resource.setId(null); // may be differnt db id
        List<Party> partyList = partyRepository.findByUuid(ownerPartyUUID);
        if (partyList.isEmpty()) {
            throw new IllegalArgumentException("cannot create resource party not found ownerPartyUUID=" + ownerPartyUUID);
        } else {
            Party resourceOwner = partyList.get(0);
            resource.setResourceOwner(resourceOwner);
            // note - will take given uuid or create a new one
            if (resource.getUuid() == null || resource.getUuid().isEmpty()) {
                resource.setUuid(UUID.randomUUID().toString());
                resource.setResourceController(ResourceAccess.INTERNAL);
            }
            resource = resourceRepository.saveAndFlush(resource);
        }

        ReplyMessage replyMessage = new ReplyMessage();
        replyMessage.setResourceList(Arrays.asList(resource));
        return replyMessage;
    }

    @Override
    @Transactional
    public ReplyMessage putUpdateResource(Resource resource) {
        resource.setId(null); // may be differnt db id
        if (resource.getUuid() == null) {
            throw new IllegalArgumentException("cannot update resource uuid=null");
        }
        List<Resource> resourceList = resourceRepository.findByUuid(resource.getUuid());
        if (resourceList.isEmpty()) {
            throw new IllegalArgumentException("cannot update resource not found uuid=" + resource.getUuid());
        }
        Resource resourceEntity = resourceList.get(0);

        // update found resource with new values
        resourceEntity = AbstractResourceMapper.INSTANCE.updateResource(resource, resourceEntity);
        resourceEntity = resourceRepository.saveAndFlush(resourceEntity);

        //create a detached resource dto for reply message
        Resource detachedResource = AbstractResourceMapper.INSTANCE.abstractResourceToResource(resourceEntity);
        ReplyMessage replyMessage = new ReplyMessage();
        replyMessage.setOffset(1);
        replyMessage.setLimit(1);
        replyMessage.setTotalCount(1L);
        replyMessage.setResourceList(Arrays.asList(detachedResource));
        return replyMessage;
    }

    @Override
    @Transactional
    public ReplyMessage getResourceByTemplate(Resource resourceSearchTemplate, Integer offset, Integer limit) {

        // TODO criteria search
       // if (resourceSearchTemplate != null ) {
        //    throw new UnsupportedOperationException("Not supported yet.");
       // }

        List<Resource> resourceList = resourceRepository.findAll();
        Long totalCount = resourceRepository.count();

        List<Resource> detachedResourceList = new ArrayList();

        for (Resource resourceEntity : resourceList) {
            Resource detachedResource = AbstractResourceMapper.INSTANCE.abstractResourceToResource(resourceEntity);
            detachedResourceList.add(detachedResource);
        }

        ReplyMessage replyMessage = new ReplyMessage();
        replyMessage.setOffset(offset);
        replyMessage.setLimit(limit);

        replyMessage.setResourceList(detachedResourceList);
        replyMessage.setTotalCount(totalCount);
        return replyMessage;

    }

    @Override
    @Transactional
    public ReplyMessage postRemoveCharacteristic(String resourceUuid, String characteristicName) {
        List<Resource> resourceList = resourceRepository.findByUuid(resourceUuid);
        if (resourceList != null && !resourceList.isEmpty()) {
            Resource resourceEntity = resourceList.get(0);
            List<Characteristic> characteristics = (resourceEntity.getCharacteristics() != null) ? resourceEntity.getCharacteristics() : new ArrayList<Characteristic>();
            Iterator<Characteristic> iterator = characteristics.iterator();
            while (iterator.hasNext()) {
                Characteristic characteristic = iterator.next();
                if (characteristicName.equals(characteristic.getName())) {
                    iterator.remove();
                }
            }
            resourceEntity.setCharacteristics(characteristics);
            resourceRepository.saveAndFlush(resourceEntity);

            // return detached entity
            Resource detachedResource = AbstractResourceMapper.INSTANCE.abstractResourceToResource(resourceEntity);
            ReplyMessage replyMessage = new ReplyMessage();
            replyMessage.setResourceList(Arrays.asList(detachedResource));
            return replyMessage;
        } else {
            throw new IllegalArgumentException("error deleting characteristic " + characteristicName + " resource not found esourceUuid=" + resourceUuid);
        }
    }

    @Override
    @Transactional
    public ReplyMessage postAddModifyCharacteristic(String resourceUuid, String characteristicName, String value, String description) {
        List<Resource> resourceList = resourceRepository.findByUuid(resourceUuid);
        if (resourceList != null && !resourceList.isEmpty()) {
            Resource resourceEntity = resourceList.get(0);
            List<Characteristic> characteristics = (resourceEntity.getCharacteristics() != null) ? resourceEntity.getCharacteristics() : new ArrayList<Characteristic>();
            //TODO debug
            if (resourceEntity.getCharacteristics() != null) {
                for (Object o : resourceEntity.getCharacteristics()) {
                    LOG.debug("******************x 0=" + o);
                    LOG.debug("***************type 0=" + o.getClass().getCanonicalName());
                }
            } else {
                LOG.debug("****************** 0=null !!");
            }

            Iterator<Characteristic> iterator = characteristics.iterator();
            boolean found = false;
            Characteristic characteristic;
            while (iterator.hasNext() && !found) {
                characteristic = iterator.next();
                if (characteristicName.equals(characteristic.getName())) {
                    // update characteristic if found
                    found = true;
                    characteristic.setDescription(description);
                    characteristic.setValue(value);
                }
            }
            // create new characteristic if not found
            if (!found) {
                characteristic = new Characteristic(characteristicName, value, description);
                characteristic.setDescription(description);
                characteristic.setValue(value);
                characteristics.add(characteristic);
            }
            resourceEntity.setCharacteristics(characteristics);
            resourceRepository.saveAndFlush(resourceEntity);

            // return detached entity
            Resource detachedResource = AbstractResourceMapper.INSTANCE.abstractResourceToResource(resourceEntity);
            ReplyMessage replyMessage = new ReplyMessage();
            replyMessage.setResourceList(Arrays.asList(detachedResource));
            return replyMessage;
        } else {
            throw new IllegalArgumentException("error updating characteristic " + characteristicName + " resource not found resourceUuid=" + resourceUuid);
        }
    }

    
    
    
//    #Add a Pilot in inventory
//@Operation(summary = "create a pilot in Inventory",
//            tags = {"resource/inventory"},
//            responses = {
//                @ApiResponse(responseCode = "200", description = "successful operation returns resource list with one entry", content = @Content(
//                        schema = @Schema(implementation = ReplyMessage.class))),
//                @ApiResponse(responseCode = "500", description = "internal server error")
//            })
//@POST
//    @Path("/Inventory")
//    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    @Transactional
//    public Response postCreateResourceInventory(Resource resource, @Context UriInfo uriInfo) {
//        try {
//            throw new UnsupportedOperationException("Not supported yet.");
//
//        } catch (Exception ex) {
//            LOG.error("error calling POST /inventory postCreateResourceInventory ", ex);
//            ReplyMessage replyMessage = new ReplyMessage();
//            replyMessage.setCode(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
//            replyMessage.setDebugMessage("error calling POST /inventory postCreateResourceInventory " + ex.getMessage());
//            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(replyMessage).build();
//        }
//    }
//
//@RequestMapping(value="create a pilot in Inventory link with pilotId{example}, method = RequestMethod.POST
//#Find a pilot/allow user to choose a pilot
//@Operation(summary = "Find pilot by uuid in inventory",
//            tags = {"resource/inventory"},
//            responses = {
//                @ApiResponse(responseCode = "200", description = "successful operation returns resource list with one entry", content = @Content(
//                        schema = @Schema(implementation = ReplyMessage.class))),
//                @ApiResponse(responseCode = "404", description = "not found"),
//                @ApiResponse(responseCode = "500", description = "internal server error")
//            })
//    @GET
//    @Path("/inventory/{uuid}")
//    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    @Transactional(readOnly = true)
//    public Response getResourceInventoryByuuid(@PathParam("uuid") String uuid, @Context UriInfo uriInfo) {
//        try {
//            ReplyMessage replyMessage = resourceInventoryService.getResourceByuuid(uuid);
//            replyMessage.setCode(Response.Status.OK.getStatusCode());
//            return Response.status(Response.Status.OK).entity(replyMessage).build();
//
//        } catch (Exception ex) {
//            LOG.error("error calling GET /inventory/{uuid} getResourceInventoryByuuid uuid=" + uuid, ex);
//            ReplyMessage replyMessage = new ReplyMessage();
//            replyMessage.setCode(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
//            replyMessage.setDebugMessage("error calling GET /inventory/{uuid} getResourceInventoryByuuid uuid=" + uuid + " " + ex.getMessage());
//            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(replyMessage).build();
//        }
//    }
//
//@RequestMapping(value="Find a pilot by uuid in inventory link with pilotId{example}, method = RequestMethod.GET
}
