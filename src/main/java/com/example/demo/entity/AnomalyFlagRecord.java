package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "anomaly_flag_records")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnomalyFlagRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long employeeId;

    private Long metricId;

    private String ruleCode;

    private String severity;

    private String details;

    private LocalDateTime flaggedAt;

    private Boolean resolved = false;

    @PrePersist
    void onFlag() {
        flaggedAt = LocalDateTime.now();
    }
}
