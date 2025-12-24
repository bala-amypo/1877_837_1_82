// src/main/java/com/example/demo/entity/ProductivityMetricRecord.java
package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "productivity_metric_records")
@Data
public class ProductivityMetricRecord {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne @JoinColumn(name = "employee_id")
    private EmployeeProfile employee;

    private LocalDate date;
    private Double hoursLogged;
    private Integer tasksCompleted;
    private Integer meetingsAttended;
    private Double productivityScore;
    private String rawDataJson;
    private LocalDateTime submittedAt;
}
