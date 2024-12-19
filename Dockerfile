# Use an official OpenJDK 17 runtime as the base image
FROM eclipse-temurin:17-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the Spring Boot JAR file into the container
COPY target/parkme-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your Spring Boot app runs on (default is 8080)
EXPOSE 8080

# Add a HEALTHCHECK instruction (optional)
HEALTHCHECK --interval=30s --timeout=10s --retries=3 \
  CMD curl -f http://localhost:8000/actuator/health || exit 1

# Define the command to run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
