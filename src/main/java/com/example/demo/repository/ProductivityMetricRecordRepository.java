// src/main/java/com/example/demo/repository/ProductivityMetricRecordRepository.java
package com.example.demo.repository;

import com.example.demo.entity.ProductivityMetricRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ProductivityMetricRecordRepository extends JpaRepository<ProductivityMetricRecord, Long> {
    List<ProductivityMetricRecord> findByEmployee_Id(Long employeeId);
    Optional<ProductivityMetricRecord> findByEmployee_IdAndDate(Long employeeId, LocalDate date);
}
