// src/main/java/com/example/demo/controller/AnomalyRuleController.java
package com.example.demo.controller;

import com.example.demo.dto.AnomalyRuleDto;
import com.example.demo.entity.AnomalyRule;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.service.AnomalyRuleService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/anomaly-rules")
public class AnomalyRuleController {

    private final AnomalyRuleService ruleService;

    public AnomalyRuleController(AnomalyRuleService ruleService) {
        this.ruleService = ruleService;
    }

    @PostMapping
    public AnomalyRule create(@RequestBody AnomalyRuleDto dto) {
        AnomalyRule rule = new AnomalyRule(dto.getRuleCode(), dto.getDescription(),
                dto.getThresholdType(), dto.getThresholdValue(), dto.getActive());
        return ruleService.createRule(rule);
    }

    @PutMapping("/{id}")
    public AnomalyRule update(@PathVariable Long id, @RequestBody AnomalyRuleDto dto) {
        AnomalyRule updated = new AnomalyRule(dto.getRuleCode(), dto.getDescription(),
                dto.getThresholdType(), dto.getThresholdValue(), dto.getActive());
        return ruleService.updateRule(id, updated);
    }

    @GetMapping("/active")
    public List<AnomalyRule> active() {
        return ruleService.getActiveRules();
    }

    @GetMapping("/{id}")
    public AnomalyRule get(@PathVariable Long id) {
        return ruleService.getAllRules().stream()
                .filter(r -> r.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Rule not found"));
    }

    @GetMapping
    public List<AnomalyRule> all() {
        return ruleService.getAllRules();
    }
}
