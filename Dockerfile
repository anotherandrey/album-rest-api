FROM openjdk:17.0-jdk
ENV APP_HOME /album-rest-api
WORKDIR "$APP_HOME"
ARG JAR=openapi/build/libs/openapi-1.0-SNAPSHOT.jar
ARG JAR_PROPS=openapi/src/main/resources/application.properties
COPY ${JAR} openapi-1.0-SNAPSHOT.jar
COPY ${JAR_PROPS} application.properties
ENTRYPOINT ["java", "-jar", "/album-rest-api/openapi-1.0-SNAPSHOT.jar", "--spring.config.import=file:/album-rest-api/application.properties"]
