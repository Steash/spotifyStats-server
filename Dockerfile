# # Use a Java base image, such as AdoptOpenJDK or OpenJDK
# # FROM openjdk:17-alpine
# # FROM adoptopenjdk:17-jdk-hotspot-bionic
# # FROM eclipse-temurin:17-jdk-alpine
# FROM openjdk:17
#
# # Set the working directory inside the container
# WORKDIR /app
#
# # Copy the compiled JAR file into the container
# COPY target/spotifyStats-0.0.1-SNAPSHOT.jar app.jar
#
# # # Expose the default port of the Spring Boot app
# # EXPOSE 8080
#
# # Specify the command to run the Spring Boot app
# CMD ["java", "-jar", "app.jar"]
