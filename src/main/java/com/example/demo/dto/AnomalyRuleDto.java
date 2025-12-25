// src/main/java/com/example/demo/dto/AnomalyRuleDto.java
package com.example.demo.dto;

import lombok.Data;

@Data
public class AnomalyRuleDto {
    private Long id;
    private String ruleCode;
    private String description;
    private String thresholdType;
    private Double thresholdValue;
    private Boolean active;
}
