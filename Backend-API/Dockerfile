# Step 1: Build the application
FROM gradle:jdk21-alpine AS builder
WORKDIR /app
COPY . .
# RUN apt-get update && apt-get install -y dos2unix && dos2unix gradlew && chmod +x gradlew
RUN gradle bootJar 

# Debugging: List contents of the build/libs directory
RUN ls -la /app/build/libs

#  2 Create a runtime image
FROM amazoncorretto:21
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
