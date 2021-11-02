/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.project.impl.rest;

/**
 *
 * @author gallenc
 */
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;

import java.util.List;
import java.util.Set;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
import org.solent.com504.project.model.party.dto.Party;
import org.solent.com504.project.model.party.service.PartyService;
import org.solent.com504.project.model.service.ServiceFacade;
import org.solent.com504.project.model.user.dto.Role;
import org.solent.com504.project.model.user.dto.User;
import org.solent.com504.project.model.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * To make the ReST interface easier to program. All of the replies are contained in ReplyMessage classes but only the fields indicated are populated with each
 * reply. All replies will contain a code and a debug message. Possible replies are: List<String> replyMessage.getStringList() AnimalList
 * replyMessage.getAnimalList() int replyMessage.getCode() replyMessage.getDebugMessage(); * @author cgallen
 */
@Component // component allows resource to be picked up
@Path("/solent-api/party/v1/")
public class PartyRestService {

    // SETS UP LOGGING 
    // note that log name will be org.solent.com504.project.impl.rest.RestService
    final static Logger LOG = LogManager.getLogger(PartyRestService.class);

    @Autowired
    private UserService userService;

    @Autowired
    private PartyService partyService;

    @Operation(summary = "Find all parties",
            tags = {"party management api"},
            responses = {
                @ApiResponse(responseCode = "200", description = "successful operation returns party list with one entry", content = @Content(
                        schema = @Schema(implementation = ReplyMessage.class))),
                @ApiResponse(responseCode = "500", description = "internal server error")
            })
    @GET
    @Path("/party")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_PLAIN})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Transactional(readOnly = true)
    public Response getPartys(@Context UriInfo uriInfo) {
        try {

            ReplyMessage replyMessage = new ReplyMessage();
            LOG.debug("/getPartys called");

            if (partyService == null) {
                throw new RuntimeException("partyService==null and has not been initialised");
            }

            List<Party> partyList = partyService.findAll();

            String requestPath = uriInfo.getAbsolutePath().toASCIIString();

            // converting to set
            Set<Party> unboundList = unbindPartyList(new LinkedHashSet(partyList), requestPath);

            // converting to list
            replyMessage.setPartyList(new ArrayList(unboundList));
            replyMessage.setSize(unboundList.size());

            replyMessage.setCode(Response.Status.OK.getStatusCode());

            return Response.status(Response.Status.OK).entity(replyMessage).build();

        } catch (Exception ex) {
            LOG.error("error calling /party getPartys ", ex);
            ReplyMessage replyMessage = new ReplyMessage();
            replyMessage.setCode(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
            replyMessage.setDebugMessage("error calling /party getPartys " + ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(replyMessage).build();
        }
    }
    
       // swagger annotations
    @Operation(summary = "Find party by uuid",
            tags = {"party/party"},
            responses = {
                @ApiResponse(responseCode = "200", description = "successful operation returns party list with one entry", content = @Content(
                        schema = @Schema(implementation = ReplyMessage.class))),
                @ApiResponse(responseCode = "404", description = "not found"),
                @ApiResponse(responseCode = "500", description = "internal server error")
            })
    @GET
    @Path("/party/{uuid}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_PLAIN})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Transactional(readOnly = true)
    public Response getParty(@PathParam("uuid") String uuid, @Context UriInfo uriInfo) {
        try {
            ReplyMessage replyMessage = new ReplyMessage();
            LOG.debug("/party  getParty  called  uuid=" + uuid);

            if (partyService == null) {
                throw new RuntimeException("partyService==null and has not been initialised");
            }

            Party party = partyService.findByUuid(uuid);
            if (party == null) {
                replyMessage.setCode(Response.Status.NOT_FOUND.getStatusCode());
                replyMessage.setDebugMessage("party uuid not found " + uuid);
                replyMessage.setPartyList(new ArrayList());
                return Response.status(Response.Status.NOT_FOUND).entity(replyMessage).build();
            }

            String requestPath = uriInfo.getAbsolutePath().toASCIIString();

            // converting to set
            Set<Party> unboundList = new LinkedHashSet();
            if (party != null) {
                unboundList = unbindPartyList(new LinkedHashSet(Arrays.asList(party)), requestPath);
            }

            // converting to list
            replyMessage.setPartyList(new ArrayList(unboundList));
            replyMessage.setSize(unboundList.size());

            replyMessage.setCode(Response.Status.OK.getStatusCode());

            return Response.status(Response.Status.OK).entity(replyMessage).build();

        } catch (Exception ex) {
            LOG.error("error calling /party getParty uuid=" + uuid, ex);
            ReplyMessage replyMessage = new ReplyMessage();
            replyMessage.setCode(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
            replyMessage.setDebugMessage("error calling /party getParty uuid=" + uuid + " " + ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(replyMessage).build();
        }
    }


    // swagger annotations
    @Operation(summary = "Create new Party",
            tags = {"party management api"},
            responses = {
                @ApiResponse(responseCode = "200", description = "successful operation returns party list with one entry", content = @Content(
                        schema = @Schema(implementation = ReplyMessage.class))),
                @ApiResponse(responseCode = "500", description = "internal server error")
            })
    @POST
    @Path("/party/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Transactional
    public Response createParty(@QueryParam("partyName") String partyName,
            @Context UriInfo uriInfo) {
        try {
            ReplyMessage replyMessage = new ReplyMessage();
            LOG.debug("/party  create party  called  partyName=" + partyName);

            if (partyService == null) {
                throw new RuntimeException("partyService==null and has not been initialised");
            }

            Party party = new Party();
            party.setEnabled(Boolean.TRUE);
            party.setFirstName(partyName);
            party.setSecondName(partyName);

            party = partyService.save(party);
            
            LOG.debug("/party  created party=" + party);

            String requestPath = uriInfo.getAbsolutePath().toASCIIString();

            // converting to set
            Set<Party> unboundList = new LinkedHashSet();
            if (party != null) {
                unboundList = unbindPartyList(new LinkedHashSet(Arrays.asList(party)), requestPath);
            }

            // converting to list
            replyMessage.setPartyList(new ArrayList(unboundList));
            replyMessage.setSize(unboundList.size());

            replyMessage.setCode(Response.Status.OK.getStatusCode());

            return Response.status(Response.Status.OK).entity(replyMessage).build();

        } catch (Exception ex) {
            LOG.error("error calling /party create party name=" + partyName, ex);
            ReplyMessage replyMessage = new ReplyMessage();
            replyMessage.setCode(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
            replyMessage.setDebugMessage("error calling /party create party name=" + partyName + " " + ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(replyMessage).build();
        }
    }

    /**
     * clones new partys and unbinds from entitymanager
     *
     * @param partyList
     * @return
     */
    public static Set<Party> unbindPartyList(Set<Party> partyList, String requestPath) {
        Set<Party> unboundParty = new LinkedHashSet();

        //decouples values from dao
        for (Party party : partyList) {
            Party newParty = new Party();
            

            // add absolute path href for user
            String uuid = party.getUuid();

            String href = requestPath.substring(0, requestPath.indexOf("/solent-api/")) + "/solent-api/party/v1/party" + "/" + uuid;
            LOG.debug("setting href for party uuid:" + uuid + " href=" + href);
            newParty.setHref(href);

            newParty.setUuid(uuid);
            newParty.setAddress(party.getAddress());
            newParty.setEnabled(party.getEnabled());
            newParty.setFirstName(party.getFirstName());
            newParty.setId(party.getId());
            newParty.setSecondName(party.getSecondName());
            newParty.setPartyRole(party.getPartyRole());
            newParty.setPartyStatus(party.getPartyStatus());
            newParty.setPartyRole(party.getPartyRole());
            
            unboundParty.add(newParty);
        }
        return unboundParty;
    }

}
