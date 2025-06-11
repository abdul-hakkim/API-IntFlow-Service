package com.intflow.apiintflowservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VersionInfo {
    @JsonProperty("version")
    private String version;

    @JsonProperty("environment")
    private String environment;

    @JsonProperty("commit_hash")
    private String commitHash;

    @JsonProperty("commit_time")
    private String commitTime;

    @JsonProperty("branch")
    private String branch;

    @JsonProperty("build_time")
    private String buildTime;

    @JsonProperty("application_name")
    private String applicationName;

    // Constructors
    public VersionInfo() {}

    public VersionInfo(String version, String environment, String commitHash,
                       String commitTime, String branch, String buildTime, String applicationName) {
        this.version = version;
        this.environment = environment;
        this.commitHash = commitHash;
        this.commitTime = commitTime;
        this.branch = branch;
        this.buildTime = buildTime;
        this.applicationName = applicationName;
    }

    // Getters and Setters
    public String getVersion() { return version; }
    public void setVersion(String version) { this.version = version; }

    public String getEnvironment() { return environment; }
    public void setEnvironment(String environment) { this.environment = environment; }

    public String getCommitHash() { return commitHash; }
    public void setCommitHash(String commitHash) { this.commitHash = commitHash; }

    public String getCommitTime() { return commitTime; }
    public void setCommitTime(String commitTime) { this.commitTime = commitTime; }

    public String getBranch() { return branch; }
    public void setBranch(String branch) { this.branch = branch; }

    public String getBuildTime() { return buildTime; }
    public void setBuildTime(String buildTime) { this.buildTime = buildTime; }

    public String getApplicationName() { return applicationName; }
    public void setApplicationName(String applicationName) { this.applicationName = applicationName; }
}