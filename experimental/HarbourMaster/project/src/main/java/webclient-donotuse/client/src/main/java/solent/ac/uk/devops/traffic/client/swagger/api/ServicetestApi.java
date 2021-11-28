package solent.ac.uk.devops.traffic.client.swagger.api;

import solent.ac.uk.devops.traffic.client.swagger.invoker.ApiException;
import solent.ac.uk.devops.traffic.client.swagger.invoker.ApiClient;
import solent.ac.uk.devops.traffic.client.swagger.invoker.Configuration;
import solent.ac.uk.devops.traffic.client.swagger.invoker.Pair;

import javax.ws.rs.core.GenericType;

import org.solent.com504.project.model.dto.ReplyMessage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2021-11-19T14:34:17.894448800Z[Europe/London]")public class ServicetestApi {
  private ApiClient apiClient;

  public ServicetestApi() {
    this(Configuration.getDefaultApiClient());
  }

  public ServicetestApi(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  public ApiClient getApiClient() {
    return apiClient;
  }

  public void setApiClient(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  /**
   * This is simply a test method to check there is a heartbeat reply message
   * simply returns an incrimenting heartbeat in debug message
   * @return ReplyMessage
   * @throws ApiException if fails to make API call
   */
  public ReplyMessage getHeartbeat() throws ApiException {
    Object localVarPostBody = null;
    // create path and map variables
    String localVarPath = "/rest/service/getHeartbeat";

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();



    final String[] localVarAccepts = {
      "application/json", "application/xml"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] {  };

    GenericType<ReplyMessage> localVarReturnType = new GenericType<ReplyMessage>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
  }
  /**
   * all this does is ask for a text &#x27;hello world&#x27; response
   * Returns text hello world
   * @return String
   * @throws ApiException if fails to make API call
   */
  public String message() throws ApiException {
    Object localVarPostBody = null;
    // create path and map variables
    String localVarPath = "/rest/service";

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();



    final String[] localVarAccepts = {
      "text/plain"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] {  };

    GenericType<String> localVarReturnType = new GenericType<String>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
  }
}
