package com.intflow.apiintflowservice.service;

import com.intflow.apiintflowservice.model.VersionInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.Instant;
import java.util.Properties;

@Service
public class VersionService {

    private static final Logger logger = LogManager.getLogger(VersionService.class);

    @Value("${spring.application.name:version-api}")
    private String applicationName;

    @Value("${app.environment:local}")
    private String environment;

    @Value("${app.version:1.0.0}")
    private String version;

    public VersionInfo getVersionInfo() {
        logger.info("Fetching version information");

        Properties gitProperties = loadGitProperties();

        String commitHash = gitProperties.getProperty("git.commit.id.abbrev", "unknown");
        String commitTime = gitProperties.getProperty("git.commit.time", "unknown");
        String branch = gitProperties.getProperty("git.branch", "unknown");
        String buildTime = Instant.now().toString();

        // Extract environment from version tag if present
        String resolvedEnvironment = extractEnvironmentFromVersion(version, environment);

        VersionInfo versionInfo = new VersionInfo(
                version,
                resolvedEnvironment,
                commitHash,
                commitTime,
                branch,
                buildTime,
                applicationName
        );

        logger.debug("Version info created: {}", versionInfo);
        return versionInfo;
    }

    private Properties loadGitProperties() {
        Properties properties = new Properties();
        try {
            ClassPathResource resource = new ClassPathResource("git.properties");
            if (resource.exists()) {
                properties.load(resource.getInputStream());
                logger.debug("Git properties loaded successfully");
            } else {
                logger.warn("git.properties file not found");
            }
        } catch (IOException e) {
            logger.error("Error loading git properties", e);
        }
        return properties;
    }

    private String extractEnvironmentFromVersion(String version, String defaultEnv) {
        // Extract environment from version tag like "v1.0.0-dev" or "v1.0.0-prod"
        if (version.contains("-")) {
            String[] parts = version.split("-");
            if (parts.length > 1) {
                String env = parts[parts.length - 1];
                logger.debug("Extracted environment '{}' from version '{}'", env, version);
                return env;
            }
        }
        logger.debug("Using default environment '{}' for version '{}'", defaultEnv, version);
        return defaultEnv;
    }
}