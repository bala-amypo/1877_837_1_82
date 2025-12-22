// src/main/java/com/example/demo/service/ProductivityMetricService.java
package com.example.demo.service;

import com.example.demo.entity.ProductivityMetricRecord;

import java.util.List;
import java.util.Optional;

public interface ProductivityMetricService {
    ProductivityMetricRecord recordMetric(ProductivityMetricRecord metric);
    ProductivityMetricRecord updateMetric(Long id, ProductivityMetricRecord updated);
    List<ProductivityMetricRecord> getMetricsByEmployee(Long employeeId);
    Optional<ProductivityMetricRecord> getMetricById(Long id);
    List<ProductivityMetricRecord> getAllMetrics();
}
