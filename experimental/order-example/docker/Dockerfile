
FROM tomcat:9.0.54-jdk11-openjdk-buster

LABEL maintainer="craig.gallen@solent.ac.uk"

ADD ./target/project-web /usr/local/tomcat/webapps/project-web

EXPOSE 8080

CMD ["catalina.sh", "run"]