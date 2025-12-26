package com.example.demo.controller;

import com.example.demo.model.ProductivityMetricRecord;
import com.example.demo.service.ProductivityMetricService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/metrics")
public class ProductivityMetricController {

    private final ProductivityMetricService service;

    public ProductivityMetricController(ProductivityMetricService service) {
        this.service = service;
    }

    @PostMapping
    public ProductivityMetricRecord record(@RequestBody ProductivityMetricRecord metric) {
        return service.recordMetric(metric);
    }

    @PutMapping("/{id}")
    public ProductivityMetricRecord update(@PathVariable Long id,
                                           @RequestBody ProductivityMetricRecord metric) {
        return service.updateMetric(id, metric);
    }

    @GetMapping("/employee/{employeeId}")
    public List<ProductivityMetricRecord> getByEmployee(@PathVariable Long employeeId) {
        return service.getMetricsByEmployee(employeeId);
    }

    @GetMapping("/{id}")
    public ProductivityMetricRecord getById(@PathVariable Long id) {
        return service.getMetricById(id)
                .orElseThrow(() -> new RuntimeException("Metric not found"));
    }

    @GetMapping
    public List<ProductivityMetricRecord> getAll() {
        return service.getAllMetrics();
    }
}
