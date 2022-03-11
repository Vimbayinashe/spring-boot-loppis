FROM openjdk:17
COPY ./target/spring-loppis-0.0.1-SNAPSHOT.jar /src/loppis/
WORKDIR /src/loppis/
# Export a port to reach this container
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "spring-loppis-0.0.1-SNAPSHOT.jar"]