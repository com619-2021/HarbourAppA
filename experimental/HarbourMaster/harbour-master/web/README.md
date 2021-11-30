
# Java Web WAR

To run Tomcat as an embedded service using Tomcat

```
mvn org.codehaus.cargo:cargo-maven2-plugin:run
```

then navigate to http://localhost:8080/project-web/index.html

# swagger UI
note url parameters can be added onto swagger to change the loading location e.g. 

http://localhost:8080/project-web/swagger-ui/index.html?url=http://localhost:8080/project-web/rest/openapi.json

http://localhost:8080/project-web/swagger-ui/index.html?configUrl=/swagger-config.json






