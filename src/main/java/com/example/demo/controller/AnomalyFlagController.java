// src/main/java/com/example/demo/controller/AnomalyFlagController.java
package com.example.demo.controller;

import com.example.demo.dto.AnomalyFlagDto;
import com.example.demo.entity.AnomalyFlagRecord;
import com.example.demo.entity.EmployeeProfile;
import com.example.demo.entity.ProductivityMetricRecord;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.EmployeeProfileRepository;
import com.example.demo.repository.ProductivityMetricRecordRepository;
import com.example.demo.service.AnomalyFlagService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/anomalies")
public class AnomalyFlagController {

    private final AnomalyFlagService flagService;
    private final EmployeeProfileRepository employeeRepo;
    private final ProductivityMetricRecordRepository metricRepo;

    public AnomalyFlagController(AnomalyFlagService flagService,
                                 EmployeeProfileRepository employeeRepo,
                                 ProductivityMetricRecordRepository metricRepo) {
        this.flagService = flagService;
        this.employeeRepo = employeeRepo;
        this.metricRepo = metricRepo;
    }

    @PostMapping
    public AnomalyFlagRecord create(@RequestBody AnomalyFlagDto dto) {
        EmployeeProfile emp = employeeRepo.findById(dto.getEmployeeId())
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        ProductivityMetricRecord metric = metricRepo.findById(dto.getMetricId())
                .orElseThrow(() -> new ResourceNotFoundException("Metric not found"));
        AnomalyFlagRecord flag = new AnomalyFlagRecord(emp, metric, dto.getRuleCode(), dto.getSeverity(), dto.getDetails());
        return flagService.flagAnomaly(flag);
    }

    @PutMapping("/{id}/resolve")
    public AnomalyFlagRecord resolve(@PathVariable Long id) {
        return flagService.resolveFlag(id);
    }

    @GetMapping("/employee/{employeeId}")
    public List<AnomalyFlagRecord> byEmployee(@PathVariable Long employeeId) {
        return flagService.getFlagsByEmployee(employeeId);
    }

    @GetMapping("/metric/{metricId}")
    public List<AnomalyFlagRecord> byMetric(@PathVariable Long metricId) {
        return flagService.getFlagsByMetric(metricId);
    }

    @GetMapping
    public List<AnomalyFlagRecord> all() {
        return flagService.getAllFlags();
    }
}
