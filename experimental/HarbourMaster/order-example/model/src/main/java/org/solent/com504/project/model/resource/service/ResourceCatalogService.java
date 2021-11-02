package org.solent.com504.project.model.resource.service;

import org.solent.com504.project.model.dto.ReplyMessage;
import org.solent.com504.project.model.resource.dto.Resource;

public interface ResourceCatalogService {

    public ReplyMessage getResourceCatalogByuuid(String uuid);

    public ReplyMessage deleteResourceCatalogByUuid(String uuid);

    public ReplyMessage postCreateResourceCatalog(Resource resource, String ownerPartyUUID);

    public ReplyMessage putUpdateResourceCatalog(Resource resource);

    public ReplyMessage getResourceCatalogByTemplate(Resource resourceSearchTemplate,Integer offset, Integer limit);
}
