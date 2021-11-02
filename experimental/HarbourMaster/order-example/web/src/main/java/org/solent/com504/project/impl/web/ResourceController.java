package org.solent.com504.project.impl.web;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.solent.com504.project.impl.validator.UserValidator;
import org.solent.com504.project.model.dto.ReplyMessage;
import org.solent.com504.project.model.party.dto.Address;
import org.solent.com504.project.model.party.dto.Party;
import org.solent.com504.project.model.party.dto.PartyRole;
import org.solent.com504.project.model.party.service.PartyService;
import org.solent.com504.project.model.resource.dto.Characteristic;
import org.solent.com504.project.model.resource.dto.Resource;
import org.solent.com504.project.model.resource.dto.ResourceAccess;
import org.solent.com504.project.model.resource.service.ResourceCatalogService;
import org.solent.com504.project.model.resource.service.ResourceInventoryService;
import org.solent.com504.project.model.user.dto.Role;
import org.solent.com504.project.model.user.dto.User;
import org.solent.com504.project.model.user.dto.UserRoles;
import org.solent.com504.project.model.user.service.SecurityService;
import org.solent.com504.project.model.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Transactional
public class ResourceController {

    final static Logger LOG = LogManager.getLogger(ResourceController.class);

    {
        LOG.debug("ResourceController created");
    }

    @Autowired
    private UserService userService;

    @Autowired
    private PartyService partyService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private ResourceCatalogService resourceCatalogService = null;

    @Autowired
    private ResourceInventoryService resourceService = null;

    @RequestMapping(value = {"/resources"}, method = RequestMethod.GET)
    public String resources(Model model) {
        LOG.debug("resource called:");
        ReplyMessage reply = resourceService.getResourceByTemplate(null, 0, 20);
        List<Resource> resourceList = reply.getResourceList();

        model.addAttribute("abstractResourceListSize", resourceList.size());
        model.addAttribute("abstractResourceList", resourceList);

        // get parties
        List<Party> partyList = partyService.findAll();
        model.addAttribute("partyListSize", partyList.size());
        model.addAttribute("partyList", partyList);

        model.addAttribute("selectedPage", "resources");
        return "resources";
    }

    @RequestMapping(value = {"/viewModifyResource"}, method = RequestMethod.GET)
    public String viewresource(Model model,
            @RequestParam(value = "abstractResourceUuid", required = true) String abstractResourceUuid, Authentication authentication) {

        LOG.debug("/viewModifyResource: abstractResourceUuid:" + abstractResourceUuid);
        String errorMessage = "";
        String message = "";

        Resource abstractResource = new Resource();
        List<Characteristic> abstractCharacteristics = new ArrayList();

        // popultate model
        List<Resource> resourceList = new ArrayList();
        ReplyMessage replyMessage = resourceService.getResourceByuuid(abstractResourceUuid);
        if (replyMessage.getResourceList() != null && !replyMessage.getResourceList().isEmpty()) {
            resourceList = replyMessage.getResourceList();
            abstractResource = resourceList.get(0);
            abstractCharacteristics = (abstractResource.getCharacteristics() != null) ? abstractResource.getCharacteristics() : abstractCharacteristics;
        } else {
            errorMessage = "error getting resource" + replyMessage.getDebugMessage();
            model.addAttribute("errorMessage", errorMessage);
        }

        model.addAttribute("abstractResourceSize", resourceList.size());
        model.addAttribute("abstractResource", abstractResource);
        model.addAttribute("abstractCharacteristics", abstractCharacteristics);

        // add message if there are any 
        model.addAttribute("errorMessage", errorMessage);
        model.addAttribute("message", message);

        model.addAttribute("selectedPage", "resources");
        return "viewModifyResource";
    }

    @RequestMapping(value = {"/viewModifyResource"}, method = RequestMethod.POST)
    public String updateresource(Model model,
            @RequestParam(value = "action", required = true) String action,
            @RequestParam(value = "abstractResourceUuid", required = false) String abstractResourceUuid,
            @RequestParam(value = "abstractResourceName", required = false) String abstractResourceName,
            @RequestParam(value = "abstractTypeName", required = false) String abstractTypeName,
            @RequestParam(value = "abstractResourceHref", required = false) String abstractResourceHref,
            @RequestParam(value = "abstractResourceResourceController", required = false) String abstractResourceResourceController,
            @RequestParam(value = "abstractResourceDescription", required = false) String abstractResourceDescription,
            @RequestParam(value = "characteristicName", required = false) String characteristicName,
            @RequestParam(value = "characteristicValue", required = false) String characteristicValue,
            @RequestParam(value = "characteristicDescription", required = false) String characteristicDescription,
            @RequestParam(value = "ownerPartyUUID", required = false) String ownerPartyUUID,
            Authentication authentication) {

        LOG.debug("/viewModifyResource: abstractResourceUuid:" + abstractResourceUuid);

        String errorMessage = "";
        String message = "";

        Resource abstractResource = new Resource();
        List<Characteristic> abstractCharacteristics = new ArrayList();
        ReplyMessage replyMessage;

        // perform actions
        if ("createAbstractResource".equals(action)) {
            replyMessage = resourceService.postCreateResource(abstractResource, ownerPartyUUID);
            if (replyMessage.getResourceList() != null && !replyMessage.getResourceList().isEmpty()) {
                abstractResource = replyMessage.getResourceList().get(0);
                abstractResourceUuid = abstractResource.getUuid();
                message = "success created resource uuid=" + abstractResourceUuid;
            } else {
                errorMessage = "error creating resource" + replyMessage.getDebugMessage();
            }
            LOG.debug("creating Resource: abstractResourceUuid=" + abstractResourceUuid);

        } else if ("updateAbstractResource".equals(action)) {
            Resource resource = new Resource();
            resource.setUuid(abstractResourceUuid);
            resource.setName(abstractResourceName);
            resource.setResourceTypeName(abstractTypeName);
            resource.setHref(abstractResourceHref);
            ResourceAccess resourceAccess = ResourceAccess.valueOf(abstractResourceResourceController);
            resource.setResourceController(resourceAccess);
            resource.setDescription(abstractResourceDescription);

            resourceService.putUpdateResource(resource);
            message = "success updated";

        } else if ("deleteAbstractResource".equals(action)) {
            replyMessage = resourceService.deleteResourceByUuid(abstractResourceUuid);
            return "redirect:/resources";

        } else if ("deleteCharacteristic".equals(action)) {
            replyMessage = resourceService.postRemoveCharacteristic(abstractResourceUuid, characteristicName);
            message = "success characteristic " + characteristicName + " deleted";

        } else if ("updateCharacteristic".equals(action)) {
            replyMessage = resourceService.postAddModifyCharacteristic(abstractResourceUuid, characteristicName, characteristicValue, characteristicDescription);
            message = "success characteristic " + characteristicName + " updated";

        } else if ("createCharacteristic".equals(action)) {
            if (characteristicName.isEmpty()) {
                errorMessage = "characteristic name cannot be blank";
            } else {
                replyMessage = resourceService.postAddModifyCharacteristic(abstractResourceUuid, characteristicName, characteristicValue, characteristicDescription);
                message = "success characteristic " + characteristicName + " added";
            }
        }

        // populate model
        List<Resource> resourceList = new ArrayList();
        replyMessage = resourceService.getResourceByuuid(abstractResourceUuid);
        if (replyMessage.getResourceList() != null && !replyMessage.getResourceList().isEmpty()) {
            resourceList = replyMessage.getResourceList();
            abstractResource = resourceList.get(0);
            abstractCharacteristics = (abstractResource.getCharacteristics() != null) ? abstractResource.getCharacteristics() : abstractCharacteristics;
        } else {
            errorMessage = "error creating resource" + replyMessage.getDebugMessage();
        }
        model.addAttribute("abstractResourceSize", resourceList.size());
        model.addAttribute("abstractResource", abstractResource);
        model.addAttribute("abstractCharacteristics", abstractCharacteristics);

        // add message if there are any 
        model.addAttribute("errorMessage", errorMessage);
        model.addAttribute("message", message);

        model.addAttribute("selectedPage", "resources");
        return "viewModifyResource";
    }

    @RequestMapping(value = {"/catalog"}, method = RequestMethod.GET)
    public String catalog(Model model) {
        LOG.debug("resource called:");
        ReplyMessage reply = resourceService.getResourceByTemplate(null, 0, 20);
        List<Resource> resourceList = reply.getResourceList();

        model.addAttribute("abstractResourceSize", resourceList.size());
        model.addAttribute("abstractResourceList", resourceList);

        model.addAttribute("selectedPage", "resources");
        return "catalog";
    }

    @RequestMapping(value = {"/viewModifyCatalog"}, method = RequestMethod.POST)
    public String updatecatalog(Model model,
            @RequestParam(value = "action", required = true) String action,
            @RequestParam(value = "abstractResourceUuid", required = true) String abstractResourceUuid,
            @RequestParam(value = "abstractResourceName", required = false) String abstractResourceName,
            @RequestParam(value = "abstractTypeName", required = false) String abstractTypeName,
            @RequestParam(value = "abstractResourceHref", required = false) String abstractResourceHref,
            @RequestParam(value = "abstractResourceResourceController", required = false) String abstractResourceResourceController,
            @RequestParam(value = "abstractResourceDescription", required = false) String abstractResourceDescription,
            @RequestParam(value = "characteristicName", required = false) String characteristicName,
            @RequestParam(value = "ownerPartyUUID", required = false) String ownerPartyUUID,
            Authentication authentication) {

        LOG.debug("/viewModifyResource: abstractResourceUuid:" + abstractResourceUuid);

        String errorMessage = "";
        String message = "";

        Resource abstractResource = new Resource();
        List<Characteristic> abstractCharacteristics = new ArrayList();
        ReplyMessage replyMessage;

        // perform actions
        if ("createAbstractResource".equals(action)) {
            replyMessage = resourceService.postCreateResource(abstractResource, ownerPartyUUID);
            if (replyMessage.getResourceList() != null && !replyMessage.getResourceList().isEmpty()) {
                abstractResource = replyMessage.getResourceList().get(0);
                message = "success created";
            } else {
                errorMessage = "error creating resource" + replyMessage.getDebugMessage();
            }

        } else if ("updateAbstractResource".equals(action)) {
            message = "success updated";

        } else if ("deleteAbstractResource".equals(action)) {
            message = "success deleted";

        } else if ("deleteCharacteristic".equals(action)) {
            message = "success characteristic " + characteristicName + " deleted";

        } else if ("updateCharacteristic".equals(action)) {
            message = "success characteristic " + characteristicName + " updated";

        } else if ("createCharacteristic".equals(action)) {
            message = "success characteristic " + characteristicName + " added";
        }

        // popultate model
        List<Resource> resourceList = new ArrayList();
        replyMessage = resourceService.getResourceByuuid(abstractResourceUuid);
        if (replyMessage.getResourceList() != null && !replyMessage.getResourceList().isEmpty()) {
            resourceList = replyMessage.getResourceList();
            abstractResource = resourceList.get(0);
            abstractCharacteristics = (abstractResource.getCharacteristics() != null) ? abstractResource.getCharacteristics() : abstractCharacteristics;
        } else {
            errorMessage = "error creating resource" + replyMessage.getDebugMessage();
        }
        model.addAttribute("abstractResourceSize", resourceList.size());
        model.addAttribute("abstractResource", abstractResource);
        model.addAttribute("abstractCharacteristics", abstractCharacteristics);

        // add message if there are any 
        model.addAttribute("errorMessage", errorMessage);
        model.addAttribute("message", message);

        model.addAttribute("selectedPage", "catalog");
        return "viewModifyResource";
    }

    private Map<String, String> selectedRolesMap(User user) {

        List<String> availableRoles = userService.getAvailableUserRoleNames();

        List<String> selectedRoles = new ArrayList();
        for (Role role : user.getRoles()) {
            selectedRoles.add(role.getName());
            LOG.debug("user " + user.toString()
                    + "roles from database:" + role.getName());
        }

        Map<String, String> selectedRolesMap = new LinkedHashMap();
        for (String availableRole : availableRoles) {
            if (selectedRoles.contains(availableRole)) {
                selectedRolesMap.put(availableRole, "checked");
                LOG.debug("availableRole " + availableRole
                        + " user " + user.toString() + " available role:checked");
            } else {
                selectedRolesMap.put(availableRole, "");
                LOG.debug("availableRole " + availableRole
                        + " user " + user.toString() + " available role:not checked");
            }
        }

        return selectedRolesMap;

    }

    /**
     * returns true if the party has the role specified
     *
     * @param role
     * @return
     */
    private boolean hasRole(String role) {
        Collection<GrantedAuthority> authorities
                = (Collection<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        boolean hasRole = false;
        for (GrantedAuthority authority : authorities) {
            hasRole = authority.getAuthority().equals(role);
            if (hasRole) {
                break;
            }
        }
        return hasRole;
    }

}
