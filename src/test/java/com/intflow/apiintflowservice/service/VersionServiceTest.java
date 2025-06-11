package com.intflow.apiintflowservice.service;

import com.intflow.apiintflowservice.model.VersionInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class VersionServiceTest {

    @InjectMocks
    private VersionService versionService;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(versionService, "applicationName", "test-app");
        ReflectionTestUtils.setField(versionService, "environment", "test");
        ReflectionTestUtils.setField(versionService, "version", "1.0.0-test");
    }

    @Test
    void getVersionInfo_ShouldReturnVersionInfo() {
        // Given
        // When
        VersionInfo result = versionService.getVersionInfo();

        // Then
        assertNotNull(result);
        assertEquals("1.0.0-test", result.getVersion());
        assertEquals("test", result.getEnvironment());
        assertEquals("test-app", result.getApplicationName());
        assertNotNull(result.getBuildTime());
    }

    @Test
    void getVersionInfo_ShouldExtractEnvironmentFromVersion() {
        // Given
        ReflectionTestUtils.setField(versionService, "version", "2.0.0-prod");

        // When
        VersionInfo result = versionService.getVersionInfo();

        // Then
        assertEquals("2.0.0-prod", result.getVersion());
        assertEquals("prod", result.getEnvironment());
    }

    @Test
    void getVersionInfo_ShouldUseDefaultEnvironmentWhenNoTagSuffix() {
        // Given
        ReflectionTestUtils.setField(versionService, "version", "1.0.0");
        ReflectionTestUtils.setField(versionService, "environment", "default");

        // When
        VersionInfo result = versionService.getVersionInfo();

        // Then
        assertEquals("1.0.0", result.getVersion());
        assertEquals("default", result.getEnvironment());
    }
}
