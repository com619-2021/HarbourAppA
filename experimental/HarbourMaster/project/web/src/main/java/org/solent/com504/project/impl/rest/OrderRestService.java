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
import org.solent.com504.project.model.order.dto.Order;
import org.solent.com504.project.model.order.service.OrderChangeRequestService;
import org.solent.com504.project.model.order.service.OrderService;
import org.solent.com504.project.model.party.service.PartyService;
import org.solent.com504.project.model.resource.dto.Resource;
import org.solent.com504.project.model.resource.dto.ResourceCatalog;
import org.solent.com504.project.model.resource.service.ResourceCatalogService;
import org.solent.com504.project.model.resource.service.ResourceInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author cgallen
 */
@Component // component allows service to be picked up
@Path("/solent-api/order/v1/")
public class OrderRestService {

    final static Logger LOG = LogManager.getLogger(OrderRestService.class);

    @Autowired
    private ResourceCatalogService resourceCatalogService = null;

    @Autowired
    private ResourceInventoryService resourceService = null;

    @Autowired
    private PartyService partyService = null;

    @Autowired
    private OrderService orderService = null;
    
    @Autowired
    private OrderChangeRequestService orderChangeRequestService = null;

    @Operation(summary = "Find order by uuid",
            tags = {"order/order"},
            responses = {
                @ApiResponse(responseCode = "200", description = "successful operation returns resource list with one entry", content = @Content(
                        schema = @Schema(implementation = ReplyMessage.class))),
                @ApiResponse(responseCode = "404", description = "not found"),
                @ApiResponse(responseCode = "500", description = "internal server error")
            })
    @GET
    @Path("/order/{uuid}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Transactional(readOnly = true)
    public Response getOrderByuuid(@PathParam("uuid") String uuid, @Context UriInfo uriInfo) {
        try {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

        } catch (Exception ex) {
            LOG.error("error calling GET /order/{uuid} getOrderByuuid uuid=" + uuid, ex);
            ReplyMessage replyMessage = new ReplyMessage();
            replyMessage.setCode(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
            replyMessage.setDebugMessage("error calling GET /order/{uuid} getOrderByuuid uuid=" + uuid + " " + ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(replyMessage).build();
        }
    }

    @Operation(summary = "Delete order by uuid",
            tags = {"order/order"},
            responses = {
                @ApiResponse(responseCode = "200", description = "successful operation returns resource list with one entry", content = @Content(
                        schema = @Schema(implementation = ReplyMessage.class))),
                @ApiResponse(responseCode = "404", description = "not found"),
                @ApiResponse(responseCode = "500", description = "internal server error")
            })
    @DELETE
    @Path("/order/{uuid}")
    @Transactional()
    public Response deleteOrderByUuid(@PathParam("uuid") String uuid, @Context UriInfo uriInfo) {
        try {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

        } catch (Exception ex) {
            LOG.error("error calling DELETE /order/{uuid} getOrderByuuid uuid=" + uuid, ex);
            ReplyMessage replyMessage = new ReplyMessage();
            replyMessage.setCode(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
            replyMessage.setDebugMessage("error calling DELETE /order/{uuid} getOrderByuuid uuid=" + uuid + " " + ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(replyMessage).build();
        }
    }

    @Operation(summary = "Create new Order resource",
            tags = {"order/order"},
            responses = {
                @ApiResponse(responseCode = "200", description = "successful operation returns resource list with one entry", content = @Content(
                        schema = @Schema(implementation = ReplyMessage.class))),
                @ApiResponse(responseCode = "500", description = "internal server error")
            })
    @POST
    @Path("/order")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Transactional
    public Response postCreateOrder(Order order, @Context UriInfo uriInfo) {
        try {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

        } catch (Exception ex) {
            LOG.error("error calling POST /order postCreateOrder ", ex);
            ReplyMessage replyMessage = new ReplyMessage();
            replyMessage.setCode(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
            replyMessage.setDebugMessage("error calling POST /order postCreateOrder " + ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(replyMessage).build();
        }
    }

    @Operation(summary = "update  Order resource",
            tags = {"order/order"},
            responses = {
                @ApiResponse(responseCode = "200", description = "successful operation returns resource list with one entry", content = @Content(
                        schema = @Schema(implementation = ReplyMessage.class))),
                @ApiResponse(responseCode = "404", description = "not found"),
                @ApiResponse(responseCode = "500", description = "internal server error")
            })
    @PUT
    @Path("/order")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Transactional
    public Response putUpdateOrder(Order order, @Context UriInfo uriInfo) {
        try {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

        } catch (Exception ex) {
            LOG.error("error calling PUT /order putUpdateOrder ", ex);
            ReplyMessage replyMessage = new ReplyMessage();
            replyMessage.setCode(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
            replyMessage.setDebugMessage("error calling PUT /order putUpdateOrder " + ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(replyMessage).build();
        }
    }

    @Operation(summary = "Find order by resource template",
            tags = {"order/order"},
            responses = {
                @ApiResponse(responseCode = "200", description = "successful operation returns resource list with one entry", content = @Content(
                        schema = @Schema(implementation = ReplyMessage.class))),
                @ApiResponse(responseCode = "404", description = "not found"),
                @ApiResponse(responseCode = "500", description = "internal server error")
            })

    // note issue - https://github.com/swagger-api/swagger-ui/issues/5388 GET request do not allow a body in swagger UI although supported in JAX-RS
    @GET
    @Path("/order")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getOrder(@QueryParam("offset") Integer offset, @QueryParam("limit") Integer limit, @Context UriInfo uriInfo) {
        try {
            ReplyMessage replyMessage = orderService.getOrderByTemplate(null, offset, limit);
            replyMessage.setCode(Response.Status.OK.getStatusCode());
            return Response.status(Response.Status.OK).entity(replyMessage).build();
        } catch (Exception ex) {
            LOG.error("error calling GET /order getOrderByTemplate ", ex);
            ReplyMessage replyMessage = new ReplyMessage();
            replyMessage.setCode(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
            replyMessage.setDebugMessage("error calling GET /order getOrderByTemplate " + ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(replyMessage).build();
        }

    }

    @Operation(summary = "Find order by order template (Should be GET but get with resources not allowed in swagger UI)",
            tags = {"order/order"},
            responses = {
                @ApiResponse(responseCode = "200", description = "successful operation returns resource list with one entry", content = @Content(
                        schema = @Schema(implementation = ReplyMessage.class))),
                @ApiResponse(responseCode = "404", description = "not found"),
                @ApiResponse(responseCode = "500", description = "internal server error")
            })

    // note issue - https://github.com/swagger-api/swagger-ui/issues/5388 GET request do not allow a body in swagger UI although supported in JAX-RS
    @POST
    @Path("/getOrderByTemplate")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Transactional(readOnly = true)
    public Response getOrderByTemplate(Order orderSearchTemplate, @QueryParam("offset") Integer offset, @QueryParam("limit") Integer limit, @Context UriInfo uriInfo) {

        try {
            ReplyMessage replyMessage = orderService.getOrderByTemplate(orderSearchTemplate, offset, limit);
            replyMessage.setCode(Response.Status.OK.getStatusCode());
            return Response.status(Response.Status.OK).entity(replyMessage).build();

        } catch (Exception ex) {
            LOG.error("error calling GET /getOrderByTemplate getOrderByTemplate ", ex);
            ReplyMessage replyMessage = new ReplyMessage();
            replyMessage.setCode(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
            replyMessage.setDebugMessage("error calling GET /getOrderByTemplate getOrderByTemplate " + ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(replyMessage).build();
        }

    }

}
