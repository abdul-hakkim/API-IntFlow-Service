package com.intflow.apiintflowservice.controller;

import com.intflow.apiintflowservice.model.VersionInfo;
import com.intflow.apiintflowservice.service.VersionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class VersionController {

    private static final Logger logger = LogManager.getLogger(VersionController.class);

    @Autowired
    private VersionService versionService;

    @GetMapping("/version")
    public ResponseEntity<VersionInfo> getVersion() {
        logger.info("GET /api/v1/version endpoint called");

        try {
            VersionInfo versionInfo = versionService.getVersionInfo();
            logger.info("Successfully retrieved version information");
            return ResponseEntity.ok(versionInfo);
        } catch (Exception e) {
            logger.error("Error retrieving version information", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        logger.debug("Health check endpoint called");
        return ResponseEntity.ok("OK");
    }
}