/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.ac.uk.exampletest.manual;

import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Ignore;
import org.solent.com504.project.model.dto.ReplyMessage;
import solent.ac.uk.devops.traffic.client.swagger.api.ServicetestApi;
import solent.ac.uk.devops.traffic.client.swagger.invoker.ApiClient;
import solent.ac.uk.devops.traffic.client.swagger.invoker.ApiException;
import solent.ac.uk.devops.traffic.client.swagger.invoker.auth.HttpBasicAuth;

/**
 *
 * @author cgallen
 */
//@Ignore // do not run
public class ExampleTest {

    //note see problem https://stackoverflow.com/questions/39724076/swagger-codegen-cli-java-client-how-to-use-it-right
    // need to add security auth definition
    final static Logger LOG = LogManager.getLogger(ExampleTest.class);

    private ServicetestApi api = null;

    @Before
    public void before() {

        ApiClient apiClient = new ApiClient();
        //String basePath = "http://globaladmin:globaladmin@localhost:8080/project-web";
        String basePath = "http://localhost:8080/project-web";
        apiClient.setBasePath(basePath);

        apiClient.setUsername("globaladmin");
        apiClient.setPassword("globaladmin");

        api = new ServicetestApi(apiClient);

    }

    @Test
    public void testMessage() {

        // http://globaladmin:globaladmin@localhost:8080/project-web/rest/service
        /// see configuration and ApiClient for details of protected String basePath = "
        try {
            String response = api.message();
            LOG.debug("api reponse " + response);
        } catch (ApiException ex) {
            String body = ex.getResponseBody();
            Map<String, List<String>> headers = ex.getResponseHeaders();
            String errmsg = "ApiException: \nbody:";
            errmsg = errmsg+body+"\nheaders:";
            for(String headerName : headers.keySet()){
                List<String> header = headers.get(headerName);
                errmsg=errmsg+"\n"+headerName+" "+header;
            }
            errmsg = errmsg+"\n";
            LOG.error("error in test: exception "+errmsg, ex);
        }

    }

    @Test
    public void testHeartbeat() {

        /// see configuration and ApiClient for details of protected String basePath = "
        try {
            ReplyMessage response = api.getHeartbeat();
            LOG.debug("api reponse " + response);
        } catch (ApiException ex) {
            LOG.error("error in test:", ex);
        }

    }
}
