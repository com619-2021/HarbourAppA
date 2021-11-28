/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.project.impl.rest;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.servers.ServerVariable;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import org.glassfish.jersey.server.ResourceConfig;

// see https://github.com/swagger-api/swagger-core/wiki/Swagger-2.X---Annotations#server
// see also https://github.com/OAI/OpenAPI-Specification/blob/3.0.1/versions/3.0.1.md#serverObject
@OpenAPIDefinition(
        tags = { //   @Tag(name="widget", description="Widget operations."),
        //   @Tag(name="gasket", description="Operations related to gaskets")
        },
        info = @Info(
                title = "Smart Port API",
                version = "v1",
                description = "Solent university devops https://github.com/com619-2021",
                contact = @Contact(
                        name = "Dr Craig Gallen",
                        email = "craig.gallen@solent.ac.uk"),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://www.apache.org/licenses/LICENSE-2.0.html")),
        servers = {
            @Server(
                    description = "default",
                    url = "/project-web"
            ),
            @Server(
                    description = "localhost",
                    url = "{schema}://{host}:{port}/project-web",
                    variables = {
                        @ServerVariable(name = "schema", description = "url schema", defaultValue = "http", allowableValues = {"http", "https"}),
                        @ServerVariable(name = "host", description = "dns hostname", defaultValue = "localhost", allowableValues = {"localhost", "org.example.com"}),
                        @ServerVariable(name = "port", description = "html port", defaultValue = "8080", allowableValues = {"8080", "443"}),})
        },
        security = {
            @SecurityRequirement(name = "basicAuth" )
        }
)
// if you dont add security scheme,  authentications.put("basicAuth", new HttpBasicAuth()); is left out of ApiClient
@SecurityScheme(name = "basicAuth", scheme = "basic", type = SecuritySchemeType.HTTP)
@ApplicationPath("/rest")
public class RestApp extends ResourceConfig {

    // produces http://localhost:8080/project-web/rest/openapi.json 
    // see https://github.com/swagger-api/swagger-core/wiki/Swagger-2.X---Getting-started
    public RestApp() {
        packages("org.solent.com504.project.impl.rest",
                "io.swagger.v3.jaxrs2.integration.resources"
        );
        // configureSwagger();
    }

    // swagger 1.5
    // see https://stackoverflow.com/questions/40480131/how-to-use-swagger-with-resourceconfig-in-jersey
//    private void configureSwagger() {
//        this.register(ApiListingResource.class);
//        this.register(SwaggerSerializers.class);
//        BeanConfig config = new BeanConfig();
//        config.setConfigId("spring-jaxrs-swagger");
//        config.setTitle("Spring Jersey jaxrs swagger integration");
//        config.setVersion("v1.0");
//        config.setBasePath("/swagger");
//        config.setResourcePackage("org.solent.com504.project.impl.rest");
//        config.setPrettyPrint(true);
//        config.setScan(true);
    // http://localhost:8080/project-web/rest/swagger/v1.0/swagger.json
    // http://localhost:8080/swagger/v1.0/swagger.json
    // }
}

// alternatve if using jaxrs directly
//public class RestApp extends Application {
//  public Set<Class<?>> getClasses() {
//    return new HashSet<Class<?>>(Arrays.asList(RestService.class));
//  }
//}
