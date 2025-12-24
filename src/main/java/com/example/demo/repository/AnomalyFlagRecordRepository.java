// src/main/java/com/example/demo/repository/AnomalyFlagRecordRepository.java
package com.example.demo.repository;

import com.example.demo.entity.AnomalyFlagRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnomalyFlagRecordRepository extends JpaRepository<AnomalyFlagRecord, Long> {
    List<AnomalyFlagRecord> findByMetric_Id(Long metricId);
    List<AnomalyFlagRecord> findByEmployee_Id(Long employeeId);
}
