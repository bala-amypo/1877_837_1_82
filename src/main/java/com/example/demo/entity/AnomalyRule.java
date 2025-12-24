// src/main/java/com/example/demo/entity/AnomalyRule.java
package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "anomaly_rules")
@Data
public class AnomalyRule {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String ruleCode;

    private String description;
    private String thresholdType;
    private Double thresholdValue;
    private Boolean active = true;
}
