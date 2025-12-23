// src/main/java/com/example/demo/service/impl/ProductivityMetricServiceImpl.java
package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.*;
import com.example.demo.service.ProductivityMetricService;
import com.example.demo.util.ProductivityCalculator;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProductivityMetricServiceImpl implements ProductivityMetricService {

    private final ProductivityMetricRecordRepository metricRepo;
    private final EmployeeProfileRepository employeeRepo;
    private final AnomalyRuleRepository ruleRepo;
    private final AnomalyFlagRecordRepository flagRepo;

    public ProductivityMetricServiceImpl(
            ProductivityMetricRecordRepository metricRepo,
            EmployeeProfileRepository employeeRepo,
            AnomalyRuleRepository ruleRepo,
            AnomalyFlagRecordRepository flagRepo
    ) {
        this.metricRepo = metricRepo;
        this.employeeRepo = employeeRepo;
        this.ruleRepo = ruleRepo;
        this.flagRepo = flagRepo;
    }

    @Override
    public ProductivityMetricRecord recordMetric(ProductivityMetricRecord metric) {
        // Ensure employee exists and active
        Long employeeId = metric.getEmployee().getId();
        EmployeeProfile employee = employeeRepo.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        if (Boolean.FALSE.equals(employee.getActive())) {
            throw new IllegalStateException("Employee not active");
        }

        // Enforce uniqueness per (employee, date)
        metricRepo.findByEmployee_IdAndDate(employeeId, metric.getDate()).ifPresent(m -> {
            throw new IllegalStateException("Metric already exists for this employee and date");
        });

        // Normalize values and compute score
        double hours = ProductivityCalculator.normalize(metric.getHoursLogged());
        int tasks = ProductivityCalculator.normalizeInt(metric.getTasksCompleted());
        int meetings = ProductivityCalculator.normalizeInt(metric.getMeetingsAttended());
        double score = ProductivityCalculator.computeScore(hours, tasks, meetings);

        metric.setHoursLogged(hours);
        metric.setTasksCompleted(tasks);
        metric.setMeetingsAttended(meetings);
        metric.setProductivityScore(score);
        metric.setSubmittedAt(LocalDateTime.now());

        ProductivityMetricRecord saved = metricRepo.save(metric);

        // Apply active rules and create flags
        List<AnomalyRule> activeRules = ruleRepo.findByActiveTrue();
        for (AnomalyRule rule : activeRules) {
            boolean fired = false;
            String severity = "LOW";
            String details = "";

            // Simple sample: SCORE_BELOW threshold
            if ("SCORE_BELOW".equalsIgnoreCase(rule.getThresholdType())) {
                if (saved.getProductivityScore() < rule.getThresholdValue()) {
                    fired = true;
                    severity = "MEDIUM";
                    details = "Score below threshold (" + rule.getThresholdValue() + ")";
                }
            }

            if (fired) {
                AnomalyFlagRecord flag = new AnomalyFlagRecord(employee, saved, rule.getRuleCode(), severity, details);
                flagRepo.save(flag);
            }
        }

        return saved;
    }

    @Override
    public ProductivityMetricRecord updateMetric(Long id, ProductivityMetricRecord updated) {
        ProductivityMetricRecord existing = metricRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Metric not found"));

        // Maintain employee link from existing
        EmployeeProfile employee = existing.getEmployee();
        if (Boolean.FALSE.equals(employee.getActive())) {
            throw new IllegalStateException("Employee not active");
        }

        // Update fields
        existing.setDate(updated.getDate());
        double hours = ProductivityCalculator.normalize(updated.getHoursLogged());
        int tasks = ProductivityCalculator.normalizeInt(updated.getTasksCompleted());
        int meetings = ProductivityCalculator.normalizeInt(updated.getMeetingsAttended());
        existing.setHoursLogged(hours);
        existing.setTasksCompleted(tasks);
        existing.setMeetingsAttended(meetings);
        existing.setRawDataJson(updated.getRawDataJson());
        existing.setProductivityScore(ProductivityCalculator.computeScore(hours, tasks, meetings));
        existing.setSubmittedAt(LocalDateTime.now());

        return metricRepo.save(existing);
    }

    @Override
    public List<ProductivityMetricRecord> getMetricsByEmployee(Long employeeId) {
        return metricRepo.findByEmployee_Id(employeeId);
    }

    @Override
    public Optional<ProductivityMetricRecord> getMetricById(Long id) {
        return metricRepo.findById(id);
    }

    @Override
    public List<ProductivityMetricRecord> getAllMetrics() {
        return metricRepo.findAll();
    }
}
