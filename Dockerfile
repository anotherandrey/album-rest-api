FROM openjdk:17.0-jdk
ENV APP_HOME /album-rest-api
WORKDIR "$APP_HOME"
ARG JAR=build/libs/album-rest-api-1.0-SNAPSHOT.jar
ARG JAR_PROPS=src/main/resources/application.properties
COPY ${JAR} album-rest-api-1.0-SNAPSHOT.jar
COPY ${JAR_PROPS} application.properties
ENTRYPOINT ["java", "-jar", "/album-rest-api/album-rest-api-1.0-SNAPSHOT.jar", "--spring.config.import=file:/album-rest-api/application.properties"]
