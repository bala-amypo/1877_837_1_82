// src/main/java/com/example/demo/service/impl/AnomalyRuleServiceImpl.java
package com.example.demo.service.impl;

import com.example.demo.entity.AnomalyRule;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.AnomalyRuleRepository;
import com.example.demo.service.AnomalyRuleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnomalyRuleServiceImpl implements AnomalyRuleService {

    private final AnomalyRuleRepository ruleRepo;

    public AnomalyRuleServiceImpl(AnomalyRuleRepository ruleRepo) {
        this.ruleRepo = ruleRepo;
    }

    @Override
    public AnomalyRule createRule(AnomalyRule rule) {
        return ruleRepo.save(rule);
    }

    @Override
    public AnomalyRule updateRule(Long id, AnomalyRule updatedRule) {
        AnomalyRule existing = ruleRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rule not found"));
        existing.setRuleCode(updatedRule.getRuleCode());
        existing.setDescription(updatedRule.getDescription());
        existing.setThresholdType(updatedRule.getThresholdType());
        existing.setThresholdValue(updatedRule.getThresholdValue());
        existing.setActive(updatedRule.getActive());
        return ruleRepo.save(existing);
    }

    @Override
    public List<AnomalyRule> getActiveRules() {
        return ruleRepo.findByActiveTrue();
    }

    @Override
    public Optional<AnomalyRule> getRuleByCode(String ruleCode) {
        return ruleRepo.findByRuleCode(ruleCode);
    }

    @Override
    public List<AnomalyRule> getAllRules() {
        return ruleRepo.findAll();
    }
}
