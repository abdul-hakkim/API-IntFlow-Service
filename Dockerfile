# Dockerfile
FROM openjdk:21-jdk-slim as builder

WORKDIR /app
COPY pom.xml .
COPY src ./src

# Install Maven
RUN apt-get update && apt-get install -y maven

# Build the application
RUN mvn clean package -DskipTests

FROM openjdk:17-jre-slim

# Create non-root user
RUN groupadd -r appgroup && useradd -r -g appgroup -u 1001 appuser

# Set working directory
WORKDIR /app

# Copy the jar file
COPY --from=builder /app/target/*.jar app.jar

# Create logs directory
RUN mkdir -p /app/logs && chown -R appuser:appgroup /app

# Switch to non-root user
USER appuser

# Expose port
EXPOSE 8080

# Health check
HEALTHCHECK --interval=30s --timeout=3s --start-period=5s --retries=3 \
  CMD curl -f http://localhost:8080/api/v1/health || exit 1

# Set JVM options
ENV JAVA_OPTS="-Xmx256m -Xms128m -Djava.security.egd=file:/dev/./urandom"

# Run the application
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
