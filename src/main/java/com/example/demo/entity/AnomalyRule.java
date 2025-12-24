// src/main/java/com/example/demo/entity/AnomalyRule.java
package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "anomaly_rules", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"ruleCode"})
})
public class AnomalyRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String ruleCode; // e.g., LOW_SCORE, SPIKE

    private String description;

    @Column(nullable = false)
    private String thresholdType; // e.g., SCORE_BELOW

    @Column(nullable = false)
    private Double thresholdValue;

    @Column(nullable = false)
    private Boolean active = true;

    public AnomalyRule() {}

    public AnomalyRule(String ruleCode, String description, String thresholdType, Double thresholdValue, Boolean active) {
        this.ruleCode = ruleCode;
        this.description = description;
        this.thresholdType = thresholdType;
        this.thresholdValue = thresholdValue;
        this.active = active;
    }

    // Getters and setters...
}
