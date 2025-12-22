// src/main/java/com/example/demo/controller/ProductivityMetricController.java
package com.example.demo.controller;

import com.example.demo.dto.ProductivityMetricDto;
import com.example.demo.entity.EmployeeProfile;
import com.example.demo.entity.ProductivityMetricRecord;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.EmployeeProfileRepository;
import com.example.demo.service.ProductivityMetricService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/metrics")
public class ProductivityMetricController {

    private final ProductivityMetricService metricService;
    private final EmployeeProfileRepository employeeRepo;

    public ProductivityMetricController(ProductivityMetricService metricService, EmployeeProfileRepository employeeRepo) {
        this.metricService = metricService;
        this.employeeRepo = employeeRepo;
    }

    @PostMapping
    public ProductivityMetricDto record(@RequestBody ProductivityMetricDto dto) {
        EmployeeProfile emp = employeeRepo.findById(dto.getEmployeeId())
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        ProductivityMetricRecord entity = new ProductivityMetricRecord(
                emp, dto.getDate(), dto.getHoursLogged(), dto.getTasksCompleted(), dto.getMeetingsAttended(), dto.getRawDataJson()
        );
        ProductivityMetricRecord saved = metricService.recordMetric(entity);
        return toDto(saved);
    }

    @PutMapping("/{id}")
    public ProductivityMetricDto update(@PathVariable Long id, @RequestBody ProductivityMetricDto dto) {
        ProductivityMetricRecord updated = new ProductivityMetricRecord();
        BeanUtils.copyProperties(dto, updated, "id", "employeeId", "productivityScore", "submittedAt");
        ProductivityMetricRecord saved = metricService.updateMetric(id, updated);
        return toDto(saved);
    }

    @GetMapping("/employee/{employeeId}")
    public List<ProductivityMetricDto> byEmployee(@PathVariable Long employeeId) {
        return metricService.getMetricsByEmployee(employeeId).stream().map(this::toDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ProductivityMetricDto get(@PathVariable Long id) {
        ProductivityMetricRecord m = metricService.getMetricById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Metric not found"));
        return toDto(m);
    }

    @GetMapping
    public List<ProductivityMetricDto> all() {
        return metricService.getAllMetrics().stream().map(this::toDto).collect(Collectors.toList());
    }

    private ProductivityMetricDto toDto(ProductivityMetricRecord m) {
        ProductivityMetricDto dto = new ProductivityMetricDto();
        dto.setId(m.getId());
        dto.setEmployeeId(m.getEmployee().getId());
        dto.setDate(m.getDate());
        dto.setHoursLogged(m.getHoursLogged());
        dto.setTasksCompleted(m.getTasksCompleted());
        dto.setMeetingsAttended(m.getMeetingsAttended());
        dto.setProductivityScore(m.getProductivityScore());
        dto.setRawDataJson(m.getRawDataJson());
        dto.setSubmittedAt(m.getSubmittedAt());
        return dto;
    }
}
