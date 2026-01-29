FROM tomcat:9.0-jdk17

# Remove default ROOT app
RUN rm -rf /usr/local/tomcat/webapps/ROOT

# Copy backend project into Tomcat ROOT
COPY attendance-backend/ /usr/local/tomcat/webapps/ROOT/

# Expose Tomcat port
EXPOSE 9090

# Start Tomcat
CMD ["catalina.sh", "run"]
