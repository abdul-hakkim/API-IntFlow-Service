# Dockerfile
FROM openjdk:21-slim as builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
COPY .git .git
# Install Maven
RUN apt-get update && apt-get install -y maven
# Build the application
RUN mvn clean package -DskipTests

FROM openjdk:21-slim
# Set working directory
WORKDIR /app
# Copy the jar file
COPY --from=builder /app/target/*.jar app.jar

# Create logs directory and set permissions for any user
RUN mkdir -p /app/logs && \
    chmod -R 777 /app/logs && \
    chmod 644 /app/app.jar

# Make the app directory accessible to any user (OpenShift assigns random UIDs)
RUN chmod -R g+rwX /app

# Install curl for health check
RUN apt-get update && apt-get install -y curl && rm -rf /var/lib/apt/lists/*

# Don't specify a USER - let OpenShift assign one
# OpenShift will run the container as a random UID in the allowed range

# Expose port
EXPOSE 8080

# Health check
HEALTHCHECK --interval=30s --timeout=3s --start-period=5s --retries=3 \
  CMD curl -f http://localhost:8080/api/v1/health || exit 1

# Set JVM options
ENV JAVA_OPTS="-Xmx256m -Xms128m -Djava.security.egd=file:/dev/./urandom"

# Run the application
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
