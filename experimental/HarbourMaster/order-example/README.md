
# base project with Party and User objects - Spring Java @Config

updated for java 11 dependencies

This is a project with DAO's based on spring MVC, jpa and springdata jpa with Hibernate and HSQLDB

The project provides user and role management (crud) and the ability to associate users with parties

### running as docker image

This only works if you are running on a machine with docker installed or on a windows machine with docker-desktop

To build the docker image from the parent project use
```
 mvn clean install -P packageAsDocker
```
once you have packaged the docker image you can run it directly using 

```
docker run -p 8080:8080 solentdevops/order-example:0.1-SNAPSHOT

```

or  as a deamon

```
docker run -d  -p 8080:8080 solentdevops/order-example:0.1-SNAPSHOT
```
The application should be available at http://-hostname-:8080/project-web/

where -hostname- is either localhost or the dns name of the server.

to see the container daemon image name use
```
docker ps
```
output will be like
```
CONTAINER ID        IMAGE                                     COMMAND            CREATED          STATUS          PORTS                
c3773b47f046        solentdevops/order-example:0.1-SNAPSHOT   "catalina.sh run"   34 seconds ago   Up 33 seconds   0.0.0.0:8080->8080/tcp, :::8080->8080/tcp
```

to see logs in running container
```
docker logs <container id>

docker logs --follow  <container id>
```

to stop the daemon container

```
docker stop  <container id>
```

to stop and remove the container image
```
docker rm -f c3773b47f046
```



