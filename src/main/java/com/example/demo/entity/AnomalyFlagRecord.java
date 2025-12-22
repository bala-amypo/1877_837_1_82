package com.example.demo.model;

public class AnomalyFlagRecord {

    private String ruleCode;
    private String severity;
    private Boolean resolved = false;
    private String details;

    public String getRuleCode() { return ruleCode; }
    public void setRuleCode(String ruleCode) { this.ruleCode = ruleCode; }

    public String getSeverity() { return severity; }
    public void setSeverity(String severity) { this.severity = severity; }

    public Boolean getResolved() { return resolved; }
    public void setResolved(Boolean resolved) { this.resolved = resolved; }

    public String getDetails() { return details; }
    public void setDetails(String details) { this.details = details; }
}
