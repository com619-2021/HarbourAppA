/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.project.impl.order.service.test;

import javax.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.solent.com504.project.impl.dao.dao.PilotDAO;
import org.solent.com504.project.impl.resource.service.PilotService;
import org.solent.com504.project.impl.service.test.ServiceTestConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

/**
 *
 * @author jake_
 * 
 * 
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
// ApplicationContext will be loaded from the OrderServiceConfig class
@ContextConfiguration(classes = ServiceTestConfiguration.class, loader = AnnotationConfigContextLoader.class)
@Transactional
public class PilotServiceTest {

    final static Logger LOG = LogManager.getLogger(PilotServiceTest.class);
    
    @Autowired
	PilotService pilotService;

	@Autowired
	PilotDAO pilotDAO;
        
        @Before
	public void daoServiceCheck() {
		
            	assertNotNull(pilotService);
		assertNotNull(pilotDAO);
        }
        
    @org.springframework.transaction.annotation.Transactional
    @Test
    public void loadServiceTest() {
        // just tests load
        LOG.debug("*** start of loadServiceTest");
        LOG.debug("*** end of loadServiceTest");
    }
}