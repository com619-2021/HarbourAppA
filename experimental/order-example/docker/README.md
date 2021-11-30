## packing docker image using tomcat container


To build the docker image from the parent project use
```
 mvn clean install -P packageAsDocker
```
once you have packaged the docker image you can run it directly using 

```
docker run -p 8080:8080 solentdevops/order-example:0.1-SNAPSHOT
```

or use the docker compose file
```

docker-compose up

# or if running as deamon

docker-compose up -d

# shutdown

docker-compose down 

# or if want to remove all data

docker-compose down -v

# to specify the docker compose file

docker-compose up -f docker-compose.yaml

```

