// src/main/java/com/example/demo/dto/ProductivityMetricDto.java
package com.example.demo.dto;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ProductivityMetricDto {
    private Long id;
    private Long employeeId;
    private LocalDate date;
    private Double hoursLogged;
    private Integer tasksCompleted;
    private Integer meetingsAttended;
    private Double productivityScore;
    private String rawDataJson;
    private LocalDateTime submittedAt;
}
