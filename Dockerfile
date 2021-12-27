FROM openjdk:17
ARG JAR_FILE=target/*.jar
RUN gradle clean && gradle build
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
