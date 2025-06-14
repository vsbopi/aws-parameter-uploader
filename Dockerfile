FROM tomcat:10.1-jdk21-temurin

# Remove default apps
RUN rm -rf /usr/local/tomcat/webapps/*

# Add your WAR to the webapps directory
COPY target/aws-param-uploader-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080

CMD ["catalina.sh", "run"]
