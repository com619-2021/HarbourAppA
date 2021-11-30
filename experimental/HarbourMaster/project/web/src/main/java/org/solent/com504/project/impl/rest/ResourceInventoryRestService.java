/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.project.impl.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.solent.com504.project.model.dto.ReplyMessage;
import org.solent.com504.project.model.party.service.PartyService;
import org.solent.com504.project.model.resource.dto.Resource;
import org.solent.com504.project.model.resource.service.ResourceCatalogService;
import org.solent.com504.project.model.resource.service.ResourceInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author cgallen
 */
@Component // component allows resource to be picked up
@Path("/solent-api/resource/v1/")
public class ResourceInventoryRestService {

    final static Logger LOG = LogManager.getLogger(ResourceInventoryRestService.class);

    @Autowired
    ResourceCatalogService resourceCatalogService = null;

    @Autowired
    ResourceInventoryService resourceInventoryService = null;

    @Autowired
    private PartyService partyService;

    @Operation(summary = "Find catalog resource by uuid",
            tags = {"resource/inventory"},
            responses = {
                @ApiResponse(responseCode = "200", description = "successful operation returns resource list with one entry", content = @Content(
                        schema = @Schema(implementation = ReplyMessage.class))),
                @ApiResponse(responseCode = "404", description = "not found"),
                @ApiResponse(responseCode = "500", description = "internal server error")
            })
    @GET
    @Path("/inventory/{uuid}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Transactional(readOnly = true)
    public Response getResourceInventoryByuuid(@PathParam("uuid") String uuid, @Context UriInfo uriInfo) {
        try {
            ReplyMessage replyMessage = resourceInventoryService.getResourceByuuid(uuid);
            replyMessage.setCode(Response.Status.OK.getStatusCode());
            return Response.status(Response.Status.OK).entity(replyMessage).build();

        } catch (Exception ex) {
            LOG.error("error calling GET /inventory/{uuid} getResourceInventoryByuuid uuid=" + uuid, ex);
            ReplyMessage replyMessage = new ReplyMessage();
            replyMessage.setCode(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
            replyMessage.setDebugMessage("error calling GET /inventory/{uuid} getResourceInventoryByuuid uuid=" + uuid + " " + ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(replyMessage).build();
        }
    }

    @Operation(summary = "Delete resource by uuid",
            tags = {"resource/inventory"},
            responses = {
                @ApiResponse(responseCode = "200", description = "successful operation returns resource list with one entry", content = @Content(
                        schema = @Schema(implementation = ReplyMessage.class))),
                @ApiResponse(responseCode = "404", description = "not found"),
                @ApiResponse(responseCode = "500", description = "internal server error")
            })
    @DELETE
    @Path("/inventory/{uuid}")
    @Transactional()
    public Response deleteResourceInventoryByUuid(@PathParam("uuid") String uuid, @Context UriInfo uriInfo) {
        try {
            ReplyMessage replyMessage = resourceInventoryService.deleteResourceByUuid(uuid);
            replyMessage.setCode(Response.Status.OK.getStatusCode());
            return Response.status(Response.Status.OK).entity(replyMessage).build();

        } catch (Exception ex) {
            LOG.error("error calling DELETE /inventory/{uuid} getResourceInventoryByuuid uuid=" + uuid, ex);
            ReplyMessage replyMessage = new ReplyMessage();
            replyMessage.setCode(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
            replyMessage.setDebugMessage("error calling DELETE /inventory/{uuid} getResourceInventoryByuuid uuid=" + uuid + " " + ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(replyMessage).build();
        }
    }

    @Operation(summary = "Create new resource",
            tags = {"resource/inventory"},
            responses = {
                @ApiResponse(responseCode = "200", description = "successful operation returns resource list with one entry", content = @Content(
                        schema = @Schema(implementation = ReplyMessage.class))),
                @ApiResponse(responseCode = "500", description = "internal server error")
            })
    @POST
    @Path("/inventory")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Transactional
    public Response postCreateResourceInventory(Resource resource, @Context UriInfo uriInfo) {
        try {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

        } catch (Exception ex) {
            LOG.error("error calling POST /inventory postCreateResourceInventory ", ex);
            ReplyMessage replyMessage = new ReplyMessage();
            replyMessage.setCode(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
            replyMessage.setDebugMessage("error calling POST /inventory postCreateResourceInventory " + ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(replyMessage).build();
        }
    }

    @Operation(summary = "update  Catalog resource",
            tags = {"resource/inventory"},
            responses = {
                @ApiResponse(responseCode = "200", description = "successful operation returns resource list with one entry", content = @Content(
                        schema = @Schema(implementation = ReplyMessage.class))),
                @ApiResponse(responseCode = "404", description = "not found"),
                @ApiResponse(responseCode = "500", description = "internal server error")
            })
    @PUT
    @Path("/inventory")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Transactional
    public Response putUpdateResourceInventory(Resource resource, @Context UriInfo uriInfo) {
        try {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

        } catch (Exception ex) {
            LOG.error("error calling PUT /inventory putUpdateResourceInventory ", ex);
            ReplyMessage replyMessage = new ReplyMessage();
            replyMessage.setCode(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
            replyMessage.setDebugMessage("error calling PUT /inventory putUpdateResourceInventory " + ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(replyMessage).build();
        }
    }

    @Operation(summary = "Find catalog resource by resource template",
            tags = {"resource/inventory"},
            responses = {
                @ApiResponse(responseCode = "200", description = "successful operation returns resource list with one entry", content = @Content(
                        schema = @Schema(implementation = ReplyMessage.class))),
                @ApiResponse(responseCode = "404", description = "not found"),
                @ApiResponse(responseCode = "500", description = "internal server error")
            })
    
    // note issue - https://github.com/swagger-api/swagger-ui/issues/5388 GET request do not allow a body in swagger UI although supported in JAX-RS
    @GET
    @Path("/inventory")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Transactional(readOnly = true)
    public Response getResourceInventory(@QueryParam("offset") Integer offset, @QueryParam("limit") Integer limit, @Context UriInfo uriInfo) {

        try {
            ReplyMessage replyMessage = resourceInventoryService.getResourceByTemplate(null, offset, limit);
            replyMessage.setCode(Response.Status.OK.getStatusCode());
            return Response.status(Response.Status.OK).entity(replyMessage).build();

        } catch (Exception ex) {
            LOG.error("error calling GET /inventory getResourceInventoryByTemplate ", ex);
            ReplyMessage replyMessage = new ReplyMessage();
            replyMessage.setCode(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
            replyMessage.setDebugMessage("error calling GET /inventory getResourceInventoryByTemplate " + ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(replyMessage).build();
        }

    }
    
       @Operation(summary = "Find catalog resource by resource template (Should be GET but get with resources not allowed in swagger UI)",
            tags = {"resource/inventory"},
            responses = {
                @ApiResponse(responseCode = "200", description = "successful operation returns resource list with one entry", content = @Content(
                        schema = @Schema(implementation = ReplyMessage.class))),
                @ApiResponse(responseCode = "404", description = "not found"),
                @ApiResponse(responseCode = "500", description = "internal server error")
            })
    
    // note issue - https://github.com/swagger-api/swagger-ui/issues/5388 GET request do not allow a body in swagger UI although supported in JAX-RS
    @POST
    @Path("/getInventoryByTemplate")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Transactional(readOnly = true)
    public Response getResourceInventoryByTemplate(Resource resourceSearchTemplate, @QueryParam("offset") Integer offset, @QueryParam("limit") Integer limit, @Context UriInfo uriInfo) {

        try {
            ReplyMessage replyMessage = resourceInventoryService.getResourceByTemplate(resourceSearchTemplate, offset, limit);
            replyMessage.setCode(Response.Status.OK.getStatusCode());
            return Response.status(Response.Status.OK).entity(replyMessage).build();

        } catch (Exception ex) {
            LOG.error("error calling GET /inventory getResourceInventoryByTemplate ", ex);
            ReplyMessage replyMessage = new ReplyMessage();
            replyMessage.setCode(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
            replyMessage.setDebugMessage("error calling GET /inventory getResourceInventoryByTemplate " + ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(replyMessage).build();
        }

    }

}
