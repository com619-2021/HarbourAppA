package solent.ac.uk.devops.traffic.client.swagger.api;

import solent.ac.uk.devops.traffic.client.swagger.invoker.ApiException;
import solent.ac.uk.devops.traffic.client.swagger.invoker.ApiClient;
import solent.ac.uk.devops.traffic.client.swagger.invoker.Configuration;
import solent.ac.uk.devops.traffic.client.swagger.invoker.Pair;

import javax.ws.rs.core.GenericType;

import org.solent.com504.project.model.dto.ReplyMessage;
import org.solent.com504.project.model.resource.dto.Resource;
import org.solent.com504.project.model.resource.dto.ResourceCatalog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2021-11-19T14:34:17.894448800Z[Europe/London]")public class ResourcecatalogApi {
  private ApiClient apiClient;

  public ResourcecatalogApi() {
    this(Configuration.getDefaultApiClient());
  }

  public ResourcecatalogApi(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  public ApiClient getApiClient() {
    return apiClient;
  }

  public void setApiClient(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  /**
   * Delete catalog resource by uuid
   * 
   * @param uuid  (required)
   * @return ReplyMessage
   * @throws ApiException if fails to make API call
   */
  public ReplyMessage deleteResourceCatalogByUuid(String uuid) throws ApiException {
    Object localVarPostBody = null;
    // verify the required parameter 'uuid' is set
    if (uuid == null) {
      throw new ApiException(400, "Missing the required parameter 'uuid' when calling deleteResourceCatalogByUuid");
    }
    // create path and map variables
    String localVarPath = "/rest/solent-api/resource/v1/catalog/{uuid}"
      .replaceAll("\\{" + "uuid" + "\\}", apiClient.escapeString(uuid.toString()));

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();



    final String[] localVarAccepts = {
      "*/*"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] {  };

    GenericType<ReplyMessage> localVarReturnType = new GenericType<ReplyMessage>() {};
    return apiClient.invokeAPI(localVarPath, "DELETE", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
  }
  /**
   * Find catalog resource by resource template
   * 
   * @param offset  (optional)
   * @param limit  (optional)
   * @return ReplyMessage
   * @throws ApiException if fails to make API call
   */
  public ReplyMessage getResourceCatalog(Integer offset, Integer limit) throws ApiException {
    Object localVarPostBody = null;
    // create path and map variables
    String localVarPath = "/rest/solent-api/resource/v1/catalog";

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    localVarQueryParams.addAll(apiClient.parameterToPairs("", "offset", offset));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "limit", limit));


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
   * Find catalog resource by resource template (Should be GET but get with resources not allowed in swagger UI)
   * 
   * @param body  (optional)
   * @param offset  (optional)
   * @param limit  (optional)
   * @return ReplyMessage
   * @throws ApiException if fails to make API call
   */
  public ReplyMessage getResourceCatalogByTemplate(ResourceCatalog body, Integer offset, Integer limit) throws ApiException {
    Object localVarPostBody = body;
    // create path and map variables
    String localVarPath = "/rest/solent-api/resource/v1/getCatalogByTemplate";

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    localVarQueryParams.addAll(apiClient.parameterToPairs("", "offset", offset));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "limit", limit));


    final String[] localVarAccepts = {
      "application/json", "application/xml"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      "application/json", "application/xml"
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] {  };

    GenericType<ReplyMessage> localVarReturnType = new GenericType<ReplyMessage>() {};
    return apiClient.invokeAPI(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
  }
  /**
   * Find catalog resource by uuid
   * 
   * @param uuid  (required)
   * @return ReplyMessage
   * @throws ApiException if fails to make API call
   */
  public ReplyMessage getResourceCatalogByuuid(String uuid) throws ApiException {
    Object localVarPostBody = null;
    // verify the required parameter 'uuid' is set
    if (uuid == null) {
      throw new ApiException(400, "Missing the required parameter 'uuid' when calling getResourceCatalogByuuid");
    }
    // create path and map variables
    String localVarPath = "/rest/solent-api/resource/v1/catalog/{uuid}"
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
   * Create new Catalog resource
   * 
   * @param body  (optional)
   * @return ReplyMessage
   * @throws ApiException if fails to make API call
   */
  public ReplyMessage postCreateResourceCatalog(Resource body) throws ApiException {
    Object localVarPostBody = body;
    // create path and map variables
    String localVarPath = "/rest/solent-api/resource/v1/catalog";

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();



    final String[] localVarAccepts = {
      "application/json", "application/xml"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      "application/json", "application/xml"
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] {  };

    GenericType<ReplyMessage> localVarReturnType = new GenericType<ReplyMessage>() {};
    return apiClient.invokeAPI(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
  }
  /**
   * update  Catalog resource
   * 
   * @param body  (optional)
   * @return ReplyMessage
   * @throws ApiException if fails to make API call
   */
  public ReplyMessage putUpdateResourceCatalog(Resource body) throws ApiException {
    Object localVarPostBody = body;
    // create path and map variables
    String localVarPath = "/rest/solent-api/resource/v1/catalog";

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();



    final String[] localVarAccepts = {
      "application/json", "application/xml"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      "application/json", "application/xml"
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] {  };

    GenericType<ReplyMessage> localVarReturnType = new GenericType<ReplyMessage>() {};
    return apiClient.invokeAPI(localVarPath, "PUT", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
  }
}
