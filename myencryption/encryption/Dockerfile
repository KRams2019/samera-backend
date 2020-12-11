# Base Image
FROM openjdk:8

# Maintainer
MAINTAINER Karri Sai

# Create DIR
RUN mkdir -p /usr/src/myapp

# COPY APPLICTAION COde
COPY target/encryption-0.0.1-SNAPSHOT.jar    /usr/src/myapp

# Setup Working DIR
WORKDIR /usr/src/myapp

#EXPOSE
EXPOSE 8081

# Start the Bot Service
CMD ["java", "-jar", "encryption-0.0.1-SNAPSHOT.jar"]
