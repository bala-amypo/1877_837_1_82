// src/main/java/com/example/demo/dto/TeamSummaryDto.java
package com.example.demo.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TeamSummaryDto {
    private Long id;
    private String teamName;
    private LocalDate summaryDate;
    private Double avgHoursLogged;
    private Double avgTasksCompleted;
    private Double avgScore;
    private Integer anomalyCount;
    private LocalDateTime generatedAt;
    // Getters and setters...
}
