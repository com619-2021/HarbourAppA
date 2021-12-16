/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.project.impl.web;

import java.util.List;
import javax.annotation.PostConstruct;
import org.solent.com504.project.impl.resource.service.HarbourOrderService;
import org.solent.com504.project.impl.resource.service.PilotService;
import org.solent.com504.project.model.dto.Pilot;
import org.solent.com504.project.model.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author jake_
 */
@Configuration
public class RestAPIController {
    private transient final org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog(this.getClass());
    
    @Autowired
	private PilotService pilotService;
    
    @Autowired
            
        private HarbourOrderService orderService;

  @PostConstruct
	public void addExamplePilots() {
            
            Pilot pilot1 = new Pilot("test","pilot", true);
            Pilot pilot2 = new Pilot("jake","ashton", true);
            Pilot pilot3 = new Pilot("joe","bloggs", true);
           
            pilotService.createNewPilot(pilot1);
            pilotService.createNewPilot(pilot2);
            pilotService.createNewPilot(pilot3);
                    
        }  
        
        
         @GetMapping("/all")
        public String showAllPilots(Model model) {
        String pilotsModelName = "pilots";

        List<Pilot> pilots = pilotService.getAllPilots();

        model.addAttribute(pilotsModelName, pilots);

        return "pages/listPilots";
    }
            
}
