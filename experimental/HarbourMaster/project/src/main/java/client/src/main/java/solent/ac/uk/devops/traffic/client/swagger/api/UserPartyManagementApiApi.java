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

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2021-11-19T14:34:17.894448800Z[Europe/London]")public class UserPartyManagementApiApi {
  private ApiClient apiClient;

  public UserPartyManagementApiApi() {
    this(Configuration.getDefaultApiClient());
  }

  public UserPartyManagementApiApi(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  public ApiClient getApiClient() {
    return apiClient;
  }

  public void setApiClient(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  /**
   * Create new Party
   * 
   * @param partyName  (optional)
   * @return ReplyMessage
   * @throws ApiException if fails to make API call
   */
  public ReplyMessage createParty(String partyName) throws ApiException {
    Object localVarPostBody = null;
    // create path and map variables
    String localVarPath = "/rest/solent-api/party/v1/party";

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    localVarQueryParams.addAll(apiClient.parameterToPairs("", "partyName", partyName));


    final String[] localVarAccepts = {
      "application/json", "application/xml"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] {  };

    GenericType<ReplyMessage> localVarReturnType = new GenericType<ReplyMessage>() {};
    return apiClient.invokeAPI(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
  }
  /**
   * Find party by uuid
   * 
   * @param uuid  (required)
   * @return ReplyMessage
   * @throws ApiException if fails to make API call
   */
  public ReplyMessage getParty(String uuid) throws ApiException {
    Object localVarPostBody = null;
    // verify the required parameter 'uuid' is set
    if (uuid == null) {
      throw new ApiException(400, "Missing the required parameter 'uuid' when calling getParty");
    }
    // create path and map variables
    String localVarPath = "/rest/solent-api/party/v1/party/{uuid}"
      .replaceAll("\\{" + "uuid" + "\\}", apiClient.escapeString(uuid.toString()));

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
   * Find all parties
   * 
   * @return ReplyMessage
   * @throws ApiException if fails to make API call
   */
  public ReplyMessage getPartys() throws ApiException {
    Object localVarPostBody = null;
    // create path and map variables
    String localVarPath = "/rest/solent-api/party/v1/party";

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
}
