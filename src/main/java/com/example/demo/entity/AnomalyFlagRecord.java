// src/main/java/com/example/demo/entity/AnomalyFlagRecord.java
package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "anomaly_flag_records")
public class AnomalyFlagRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Employee affected
    @ManyToOne(optional = false)
    @JoinColumn(name = "employee_id")
    private EmployeeProfile employee;

    // Metric that triggered
    @ManyToOne(optional = false)
    @JoinColumn(name = "metric_id")
    private ProductivityMetricRecord metric;

    @Column(nullable = false)
    private String ruleCode;

    @Column(nullable = false)
    private String severity; // LOW, MEDIUM, HIGH

    @Lob
    private String details;

    @Column(nullable = false)
    private LocalDateTime flaggedAt;

    @Column(nullable = false)
    private Boolean resolved = false;

    public AnomalyFlagRecord() {}

    public AnomalyFlagRecord(EmployeeProfile employee, ProductivityMetricRecord metric,
                             String ruleCode, String severity, String details) {
        this.employee = employee;
        this.metric = metric;
        this.ruleCode = ruleCode;
        this.severity = severity;
        this.details = details;
        this.flaggedAt = LocalDateTime.now();
        this.resolved = false;
    }

    // Getters and setters...
}
