# Build Stage
FROM gradle:7.3.3-jdk17 AS build
USER root

## customize minimal jre image here
RUN jlink \
    --module-path "$JAVA_HOME/jmods" \
    --add-modules java.compiler,java.sql,java.naming,java.management,java.instrument,java.rmi,java.desktop,jdk.internal.vm.compiler.management,java.xml.crypto,java.scripting,java.security.jgss,jdk.httpserver,java.net.http,jdk.naming.dns,jdk.crypto.cryptoki,jdk.unsupported \
    --verbose \
    --strip-debug \
    --compress 2 \
    --no-header-files \
    --no-man-pages \
    --output /opt/custom-jre

WORKDIR /app
COPY . .
RUN chmod u+x ./gradlew && ./gradlew clean bootJar --no-daemon

# Final Stage
FROM ubuntu:20.04
## copy files from build images with jre
COPY --from=build /opt/custom-jre /opt/java
ENV JAVA_HOME /opt/java
ENV PATH "$PATH:$JAVA_HOME/bin"

## java runtime env
ENV API_ENV local

## expose 8088 port
EXPOSE 8088

# create non-root user and switch to non-root user
RUN useradd -ms /bin/bash app
USER app

## copy jar from build stage
WORKDIR /app
COPY --from=build /app/build/libs/*.jar ./app.jar

## execute java jar here
CMD ["java", "-jar", "-DAppLogDir=/opt/log", "./app.jar"]
