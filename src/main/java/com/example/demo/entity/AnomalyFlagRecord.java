// src/main/java/com/example/demo/entity/AnomalyFlagRecord.java
package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "anomaly_flag_records")
@Data
public class AnomalyFlagRecord {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne @JoinColumn(name = "employee_id")
    private EmployeeProfile employee;

    @ManyToOne @JoinColumn(name = "metric_id")
    private ProductivityMetricRecord metric;

    private String ruleCode;
    private String severity;
    private String details;
    private LocalDateTime flaggedAt;
    private Boolean resolved = false;
}
