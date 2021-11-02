
# base project with Party and User objects - Spring Java @Config

updated for java 11 dependencies

This is a project with DAO's based on spring MVC, jpa and springdata jpa with Hibernate and HSQLDB

The project provides user and role management (crud) and the ability to associate users with parties

### running as docker image

This only works if you are running on a machien with docker installed or on a windows machien with docker-desktop

To build the docker image from the parent project use
```
 mvn clean install -P packageAsDocker
```
once you have packaged the docker image you can run it directly using 

```
docker run -p 8080:8080 solentdevops/user-example:0.1-SNAPSHOT

```

or  as a deamon

```
docker run -d  -p 8080:8080 solentdevops/user-example:0.1-SNAPSHOT
```

to see the container daemon image name use
```
docker ps
```
output will be like
```
CONTAINER ID        IMAGE                                     COMMAND            CREATED          STATUS          PORTS                
c3773b47f046        solentdevops/user-example:0.1-SNAPSHOT   "catalina.sh run"   34 seconds ago   Up 33 seconds   0.0.0.0:8080->8080/tcp, :::8080->8080/tcp
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

## todo
* get  ReST interface working with spring security
* make USER crud service - create, retrieve, update, delete user assign roles to user, change password
* make USER pages for create and modify user and assign user to roles
* make PARTY pages to create and modify parties and assign users to parties
* make PARTY Role service
* make ReST interface for users and party
* make properties file to allow different databases and passwords
* make user role service use DAO and not spring repository

## USER roles: 

* ANONYMOUS (basic access and possible registration_
* USER (can take basic user role for assigned party)
* PARTY_ADMIN (can take admin role but only for assigned parties)
* ADMIN (can do anything) 
* REST_USER

PARTY roles: bank, auction, grower, buyer, catalog
PARTY user roles: depends upon user


