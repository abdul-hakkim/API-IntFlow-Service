package com.intflow.apiintflowservice.controller;

import com.intflow.apiintflowservice.model.VersionInfo;
import com.intflow.apiintflowservice.service.VersionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(VersionController.class)
@ActiveProfiles("test")
class VersionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private VersionService versionService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getVersion_ShouldReturnVersionInfo() throws Exception {
        VersionInfo mockVersionInfo = new VersionInfo(
                "1.0.0-test",
                "test",
                "abc123",
                "2023-12-01T10:00:00Z",
                "main",
                "2023-12-01T10:00:00Z",
                "test-app"
        );
        when(versionService.getVersionInfo()).thenReturn(mockVersionInfo);

        mockMvc.perform(get("/api/v1/version"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.version").value("1.0.0-test"))
                .andExpect(jsonPath("$.environment").value("test"))
                .andExpect(jsonPath("$.commit_hash").value("abc123"))
                .andExpect(jsonPath("$.application_name").value("test-app"));
    }

    @Test
    void getVersion_ShouldReturnInternalServerErrorOnException() throws Exception {
        when(versionService.getVersionInfo()).thenThrow(new RuntimeException("Test exception"));

        mockMvc.perform(get("/api/v1/version"))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void health_ShouldReturnOk() throws Exception {
        mockMvc.perform(get("/api/v1/health"))
                .andExpect(status().isOk())
                .andExpect(content().string("OK"));
    }
}