// src/main/java/com/example/demo/dto/AnomalyFlagDto.java
package com.example.demo.dto;

import java.time.LocalDateTime;

public class AnomalyFlagDto {
    private Long id;
    private Long employeeId;
    private Long metricId;
    private String ruleCode;
    private String severity;
    private String details;
    private LocalDateTime flaggedAt;
    private Boolean resolved;
    // Getters and setters...
}
