/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.project.impl.dao.dao.test;

import org.solent.com504.project.impl.dao.order.springdata.test.*;
import org.solent.com504.project.impl.dao.resource.springdata.test.*;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.solent.com504.project.impl.dao.party.springdata.test.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.runner.RunWith;

import org.solent.com504.project.model.party.dto.Party;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import org.solent.com504.project.impl.dao.repository.PilotRepository;
import org.solent.com504.project.impl.dao.dao.PilotDAO;
import org.solent.com504.project.impl.dao.resource.springdata.ResourceCatalogRepository;
import org.solent.com504.project.impl.dao.resource.springdata.ResourceRepository;
import org.solent.com504.project.impl.dao.spring.test.DAOTestConfiguration;

import org.solent.com504.project.model.resource.dto.Characteristic;
import org.solent.com504.project.model.resource.dto.Resource;
import org.solent.com504.project.model.resource.dto.ResourceAccess;
import org.solent.com504.project.model.resource.dto.ResourceCatalog;
import org.solent.com504.project.model.utilities.PrintOutJson;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

/**
 *
 * @author cgallen
 */
@RunWith(SpringJUnit4ClassRunner.class)
// ApplicationContext will be loaded from the DAOTestConfiguration class
@ContextConfiguration(classes = DAOTestConfiguration.class, loader = AnnotationConfigContextLoader.class)
public class PilotDAOTest {

    final static Logger LOG = LogManager.getLogger(PilotDAOTest.class);

     @Autowired
     private PilotRepository pilotRepository = null;
     
     @Autowired
	private PilotDAO pilotDAO;
    
    
    
    @Before
    public void before() {
        LOG.debug("before test running");
        
        assertNotNull(pilotRepository);
        assertNotNull(pilotDAO);
        
        
        LOG.debug("before test complete");
    }

    @Transactional
    @Test
    public void loadRepositoryTest() {
        // just tests load
        LOG.debug("*** start of loadRepositoryTest");
        LOG.debug("*** end of loadRepositoryTest");
    }


    }


