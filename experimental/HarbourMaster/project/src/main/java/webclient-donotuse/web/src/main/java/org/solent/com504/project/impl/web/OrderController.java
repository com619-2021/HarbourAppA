package org.solent.com504.project.impl.web;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.solent.com504.project.impl.validator.UserValidator;
import org.solent.com504.project.model.dto.ReplyMessage;
import org.solent.com504.project.model.order.dto.Order;
import org.solent.com504.project.model.order.dto.OrderChangeRequestHref;
import org.solent.com504.project.model.order.dto.OrderHref;
import org.solent.com504.project.model.order.dto.OrderStatus;
import org.solent.com504.project.model.party.dto.Address;
import org.solent.com504.project.model.party.dto.Party;
import org.solent.com504.project.model.party.dto.PartyRole;
import org.solent.com504.project.model.party.service.PartyService;
import org.solent.com504.project.model.resource.dto.Characteristic;
import org.solent.com504.project.model.resource.dto.Resource;
import org.solent.com504.project.model.resource.dto.ResourceAccess;
import org.solent.com504.project.model.resource.dto.ResourceCatalog;
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
public class OrderController {

    final static Logger LOG = LogManager.getLogger(OrderController.class);

    final static DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm a z");

    {
        LOG.debug("OrderController created");
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

    Order mockOrder() {
        /*
    private Long id;                    
    private String href;
    private String uuid;
    private String name;
    private OrderStatus status;
    private ResourceAccess resourceAccess;                    
    private Date orderDate;
    private Date startDate;
    private Date endDate;
    private String description; 
     private List<OrderHref> subOrders;
   private PartyHref orderOwner;
    private List<OrderChangeRequestHref> changeRequests;
    private OrderHref parentOrder;
    private List<ResourceHref> resourceOrService;
         */
        Order tmporder = new Order();
        tmporder.setDescription("my order");
        tmporder.setStartDate(new Date());
        tmporder.setHref("http://tmp");
        tmporder.setId(1L);
        tmporder.setUuid(UUID.randomUUID().toString());
        tmporder.setStatus(OrderStatus.PLACED);
        tmporder.setResourceAccess(ResourceAccess.INTERNAL);
        OrderHref parent = new OrderHref();
        tmporder.setParentOrder(parent);
        tmporder.setSubOrders(Arrays.asList(parent, parent));

        OrderChangeRequestHref changehref1 = new OrderChangeRequestHref();
        changehref1.setUuid(UUID.randomUUID().toString());
        changehref1.setName("change 1");
        changehref1.setRequestDate(new Date());
        OrderChangeRequestHref changehref2 = new OrderChangeRequestHref();
        changehref2.setUuid(UUID.randomUUID().toString());
        changehref2.setName("change 2");
        changehref2.setRequestDate(new Date());

        List<OrderChangeRequestHref> changeRequests = Arrays.asList(changehref1, changehref2);

        tmporder.setChangeRequests(changeRequests);
        return tmporder;
    }

    // ***************************
    // Methods to modify catalog
    // ***************************
    /**
     *
     * @param model
     * @return
     */
    @RequestMapping(value = {"/order"}, method = RequestMethod.GET)
    public String catalog(Model model) {
        LOG.debug("order called:");

        Order tmporder = mockOrder();

        List<Order> orderList = Arrays.asList(tmporder);

        int orderListSize = orderList.size();
        model.addAttribute("orderListSize", orderListSize);
        model.addAttribute("orderList", orderList);
        model.addAttribute("dateFormat", df);

        model.addAttribute("selectedPage", "order");
        return "orders";
    }

    @RequestMapping(value = {"/viewModifyOrder"}, method = RequestMethod.GET)
    public String vieworder(Model model,
            @RequestParam(value = "orderUuid", required = true) String orderUuid,
            @RequestParam(value = "changeRequestUUID", required = false) String changeRequestUUID,
            Authentication authentication) {

        LOG.debug("/viewModifyOrder: abstractResourceUuid:" + orderUuid);
        String errorMessage = "";
        String message = "";

        Order order = mockOrder();

        model.addAttribute("order", order);

        model.addAttribute("changeOrder", order);
        model.addAttribute("changeRequestUUID", changeRequestUUID);

        // add message if there are any 
        model.addAttribute("errorMessage", errorMessage);
        model.addAttribute("message", message);
        
        model.addAttribute("resourceAccessValues",ResourceAccess.values() );
        model.addAttribute("orderStatusValues", OrderStatus.values());
        model.addAttribute("allowChangeButtons", true);
        model.addAttribute("selectedPage", "order");
        return "viewModifyOrder";
    }

    @RequestMapping(value = {"/viewModifyOrder"}, method = RequestMethod.POST)
    public String updateorder(Model model,
            @RequestParam(value = "action", required = true) String action,
            @RequestParam(value = "orderUuid", required = true) String orderUuid, Authentication authentication) {

        LOG.debug("/viewModifyOrder: orderUuid:" + orderUuid);
        String errorMessage = "";
        String message = "";

        // add message if there are any 
        model.addAttribute("errorMessage", errorMessage);
        model.addAttribute("message", message);

        model.addAttribute("resourceAccessValues",ResourceAccess.values() );
        model.addAttribute("orderStatusValues", OrderStatus.values());
        model.addAttribute("allowChangeButtons", true);
        model.addAttribute("selectedPage", "order");
        return "viewModifyOrder";
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
