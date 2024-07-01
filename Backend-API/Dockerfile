# Step 1: Build the application
FROM openjdk:21-jdk AS builder
WORKDIR /app
COPY . .
RUN apt-get update && apt-get install -y wget \
    && wget https://services.gradle.org/distributions/gradle-7.5.1-bin.zip \
    && unzip gradle-7.5.1-bin.zip -d /opt \
    && ln -s /opt/gradle-7.5.1/bin/gradle /usr/bin/gradle \
    && gradle build --no-daemon --stacktrace --info

# Step 2: Create a runtime image
FROM amazoncorretto:21
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
