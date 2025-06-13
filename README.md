```markdown
# InfFlow- Spring Boot Application

A simple Spring Boot REST API that returns version information dynamically based on release tags with environment names and commit information.

## Features

- **Dynamic Version Information**: Extracts version and environment from Git tags
- **Comprehensive Logging**: Log4j2 integration with file rotation
- **Automated Testing**: Unit tests with JUnit 5 and integration tests with Karate
- **CI/CD Pipeline**: GitHub Actions with automated deployment to OpenShift
- **Security Scanning**: CodeQL and Trivy integration
- **AI-Assisted Reviews**: Automated PR reviews with OpenAI

## Quick Start

### Prerequisites
- Java 21
- Maven 3.6+
- Docker (optional)
- OpenShift CLI (for deployment)

### Local Development

1. **Clone the repository**
   ```bash
   git clone https://github.com/your-org/version-api.git
   cd version-api
   ```

2. **Run the application**
   ```bash
   ./mvnw spring-boot:run
   ```

3. **Test the API**
   ```bash
   curl http://localhost:8080/api/v1/version
   ```

### IntelliJ IDEA Setup

1. **Import Project**
    - Open IntelliJ IDEA
    - File → Open → Select the project directory
    - Choose "Import project from external model" → Maven

2. **Configure Run Configuration**
    - Run → Edit Configurations
    - Add new "Spring Boot" configuration
    - Main class: `com.example.versionapi.VersionApiApplication`
    - Program arguments: `--spring.profiles.active=dev`

3. **Enable Annotation Processing**
    - File → Settings → Build, Execution, Deployment → Compiler → Annotation Processors
    - Check "Enable annotation processing"

4. **Configure Code Style**
    - File → Settings → Editor → Code Style → Java
    - Import the provided code style configuration

### Testing

```bash
# Run unit tests
./mvnw test

# Run integration tests
./mvnw verify

# Run Karate tests
./mvnw test -Dtest=karate.APiIntFlowTest
```

### Environment Variables

| Variable | Description | Default |
|----------|-------------|---------|
| `APP_VERSION` | Application version | `1.0.0` |
| `APP_ENVIRONMENT` | Environment name | `local` |
| `SERVER_PORT` | Server port | `8080` |
| `SPRING_PROFILES_ACTIVE` | Active Spring profile | `dev` |

### API Endpoints

- `GET /api/v1/version` - Returns version information
- `GET /api/v1/health` - Health check endpoint
- `GET /actuator/health` - Spring Boot health endpoint

### Deployment

The application automatically deploys to OpenShift when:
- Code is pushed to `main` branch
- A new tag starting with `v` is created (e.g., `v1.0.0-prod`)

### Version Tag Format

Use semantic versioning with environment suffix:
- `v1.0.0-dev` - Development environment
- `v1.0.0-test` - Test environment
- `v1.0.0-prod` - Production environment

## Contributing

1. Create a feature branch
2. Make your changes
3. Add tests
4. Submit a pull request

The AI-assisted review system will automatically analyze your changes.

## License

This project is licensed under the MIT License.
```