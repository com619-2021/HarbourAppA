/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.project.impl.resource.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.solent.com504.project.impl.dao.party.springdata.PartyRepository;
import org.solent.com504.project.impl.dao.resource.springdata.ResourceCatalogRepository;
import org.solent.com504.project.impl.dao.resource.springdata.ResourceRepository;
import org.solent.com504.project.model.dto.ReplyMessage;
import org.solent.com504.project.model.resource.dto.AbstractResourceMapper;
import org.solent.com504.project.model.resource.dto.Characteristic;
import org.solent.com504.project.model.resource.dto.Resource;
import org.solent.com504.project.model.resource.dto.ResourceAccess;
import org.solent.com504.project.model.resource.dto.ResourceCatalog;

import org.solent.com504.project.model.resource.service.ResourceCatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author cgallen
 */
@Service
public class ResourceCatalogServiceImpl implements ResourceCatalogService {

    final static Logger LOG = LogManager.getLogger(ResourceCatalogServiceImpl.class);

    @Autowired
    private PartyRepository partyRepository = null;

    @Autowired
    private ResourceRepository resourceRepository = null;

    @Autowired
    private ResourceCatalogRepository resourceCatalogRepository = null;


    @Override
    @Transactional
    public ReplyMessage getResourceCatalogByuuid(String uuid) {

        ReplyMessage replyMessage = new ReplyMessage();

        List<ResourceCatalog> resourceCatalogList = resourceCatalogRepository.findByUuid(uuid);
        if (resourceCatalogList.isEmpty()) {
            throw new IllegalArgumentException("cannot find catalog resource uuid not found=" + uuid);
        }
        ResourceCatalog resourceCatalogEntity = resourceCatalogList.get(0);

        //create a detached resource dto for reply message
        ResourceCatalog detachedResource = AbstractResourceMapper.INSTANCE.abstractResourceToResourceCatalog(resourceCatalogEntity);
        replyMessage.setResourceCatalogList(Arrays.asList(detachedResource));
        replyMessage.setOffset(0);
        replyMessage.setLimit(1);
        replyMessage.setTotalCount(1L);
        return replyMessage;
    }

    @Override
    @Transactional
    public ReplyMessage deleteResourceCatalogByUuid(String uuid) {

        List<ResourceCatalog> resourceCatalogList = resourceCatalogRepository.findByUuid(uuid);
        if (resourceCatalogList.isEmpty()) {
            throw new IllegalArgumentException("cannot delete catalog resource uuid not found=" + uuid);
        }
        resourceCatalogRepository.delete(resourceCatalogList.get(0));
        return new ReplyMessage();
    }

    @Override
    @Transactional
    public ReplyMessage postCreateResourceCatalog(ResourceCatalog resource) {
        resource.setId(null); // may be differnt db id

        // note - will take given uuid or create a new one
        if (resource.getUuid() == null || resource.getUuid().isEmpty()) {
            resource.setUuid(UUID.randomUUID().toString());
            // simple to set an unique initial type name
            Long t = new Date().getTime();
            resource.setResourceTypeName("initial name "+t );
            resource.setResourceController(ResourceAccess.INTERNAL);
        }
        resource = resourceCatalogRepository.saveAndFlush(resource);

        ReplyMessage replyMessage = new ReplyMessage();
        replyMessage.setResourceCatalogList(Arrays.asList(resource));
        return replyMessage;
    }

    @Override
    @Transactional

    public ReplyMessage putUpdateResourceCatalog(ResourceCatalog resourceCatalog) {

        resourceCatalog.setId(null); // may be differnt db id
        if (resourceCatalog.getUuid() == null) {
            throw new IllegalArgumentException("cannot update resource uuid=null");
        }
        List<ResourceCatalog> resourceCatalogList = resourceCatalogRepository.findByUuid(resourceCatalog.getUuid());
        if (resourceCatalogList.isEmpty()) {
            throw new IllegalArgumentException("cannot update resource not found uuid=" + resourceCatalog.getUuid());
        }
        ResourceCatalog resourceCatalogEntity = resourceCatalogList.get(0);

        // update found resourceCatalog with new values
        resourceCatalogEntity = AbstractResourceMapper.INSTANCE.updateCatalog(resourceCatalog, resourceCatalogEntity);
        resourceCatalogEntity = resourceCatalogRepository.saveAndFlush(resourceCatalogEntity);

        //create a detached resourceCatalog dto for reply message
        ResourceCatalog detachedResource = AbstractResourceMapper.INSTANCE.abstractResourceToResourceCatalog(resourceCatalogEntity);
        ReplyMessage replyMessage = new ReplyMessage();
        replyMessage.setOffset(1);
        replyMessage.setLimit(1);
        replyMessage.setTotalCount(1L);
        replyMessage.setResourceCatalogList(Arrays.asList(detachedResource));
        return replyMessage;
    }

    @Override
    @Transactional
    public ReplyMessage getResourceCatalogByTemplate(ResourceCatalog resourceSearchTemplate, Integer offset, Integer limit) {

        // TODO criteria search
        // if (resourceSearchTemplate != null ) {
        //    throw new UnsupportedOperationException("Not supported yet.");
        // }
        List<ResourceCatalog> resourceCatalogList = resourceCatalogRepository.findAll();
        Long totalCount = resourceCatalogRepository.count();

        List<ResourceCatalog> detachedResourceCatalogList = new ArrayList();

        for (ResourceCatalog resourceCatalogEntity : resourceCatalogList) {
            ResourceCatalog detachedResource = AbstractResourceMapper.INSTANCE.abstractResourceToResourceCatalog(resourceCatalogEntity);
            detachedResourceCatalogList.add(detachedResource);
        }

        ReplyMessage replyMessage = new ReplyMessage();
        replyMessage.setOffset(offset);
        replyMessage.setLimit(limit);

        replyMessage.setResourceCatalogList(detachedResourceCatalogList);
        replyMessage.setTotalCount(totalCount);
        return replyMessage;

    }

    @Override
    @Transactional
    public ReplyMessage postRemoveCharacteristic(String resourceUuid, String characteristicName) {
        List<ResourceCatalog> resourceCatalogList = resourceCatalogRepository.findByUuid(resourceUuid);
        if (resourceCatalogList != null && !resourceCatalogList.isEmpty()) {
            ResourceCatalog resourceEntity = resourceCatalogList.get(0);
            List<Characteristic> characteristics = (resourceEntity.getCharacteristics() != null) ? resourceEntity.getCharacteristics() : new ArrayList<Characteristic>();
            Iterator<Characteristic> iterator = characteristics.iterator();
            while (iterator.hasNext()) {
                Characteristic characteristic = iterator.next();
                if (characteristicName.equals(characteristic.getName())) {
                    iterator.remove();
                }
            }
            resourceEntity.setCharacteristics(characteristics);
            resourceCatalogRepository.saveAndFlush(resourceEntity);

            // return detached entity
            ResourceCatalog detachedResource = AbstractResourceMapper.INSTANCE.abstractResourceToResourceCatalog(resourceEntity);
            ReplyMessage replyMessage = new ReplyMessage();
            replyMessage.setResourceCatalogList(Arrays.asList(detachedResource));
            return replyMessage;
        } else {
            throw new IllegalArgumentException("error deleting characteristic " + characteristicName + " resource not found esourceUuid=" + resourceUuid);
        }
    }

    @Override
    @Transactional
    public ReplyMessage postAddModifyCharacteristic(String resourceUuid, String characteristicName, String value, String description) {
        List<ResourceCatalog> resourceCatalogList = resourceCatalogRepository.findByUuid(resourceUuid);
        if (resourceCatalogList != null && !resourceCatalogList.isEmpty()) {
            ResourceCatalog resourceCatalogEntity = resourceCatalogList.get(0);
            List<Characteristic> characteristics = (resourceCatalogEntity.getCharacteristics() != null) ? resourceCatalogEntity.getCharacteristics() : new ArrayList<Characteristic>();
            //TODO debug
            if (resourceCatalogEntity.getCharacteristics() != null) {
                for (Object o : resourceCatalogEntity.getCharacteristics()) {
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
            resourceCatalogEntity.setCharacteristics(characteristics);
            resourceCatalogRepository.saveAndFlush(resourceCatalogEntity);

            // return detached entity
            ResourceCatalog detachedResource = AbstractResourceMapper.INSTANCE.abstractResourceToResourceCatalog(resourceCatalogEntity);
            ReplyMessage replyMessage = new ReplyMessage();
            replyMessage.setResourceCatalogList(Arrays.asList(detachedResource));
            return replyMessage;
        } else {
            throw new IllegalArgumentException("error updating characteristic " + characteristicName + " resource not found resourceUuid=" + resourceUuid);
        }
    }

}
