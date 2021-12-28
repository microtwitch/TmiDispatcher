FROM gradle:7.3.3-jdk17-alpine

# install glibc for gradle-grpc-plugin
RUN wget -q -O /etc/apk/keys/sgerrand.rsa.pub https://alpine-pkgs.sgerrand.com/sgerrand.rsa.pub
RUN wget https://github.com/sgerrand/alpine-pkg-glibc/releases/download/2.34-r0/glibc-2.34-r0.apk
RUN apk add glibc-2.34-r0.apk

COPY settings.gradle .
COPY build.gradle .
COPY gradle gradle
COPY src src

RUN gradle build

FROM openjdk:17-slim
COPY --from=0 /home/gradle/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
