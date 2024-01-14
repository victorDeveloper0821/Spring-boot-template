# Build Stage
FROM gradle:7.3.3-jdk17 AS build
WORKDIR /app
COPY . .
RUN chmod u+x ./gradlew && ./gradlew clean build --no-daemon

# Final Stage
FROM openjdk:17-jdk-slim
WORKDIR /app
EXPOSE 8088
COPY --from=build /app/build/libs/*.jar ./app.jar

CMD ["java", "-jar", "-DAppLogDir=/opt/log", "./app.jar"]
