package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "anomaly_rules")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnomalyRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String ruleCode;

    private String description;

    private String thresholdType;

    private Double thresholdValue;

    private Boolean active = true;
}
