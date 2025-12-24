// src/main/java/com/example/demo/service/impl/AnomalyFlagServiceImpl.java
package com.example.demo.service.impl;

import com.example.demo.entity.AnomalyFlagRecord;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.AnomalyFlagRecordRepository;
import com.example.demo.service.AnomalyFlagService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnomalyFlagServiceImpl implements AnomalyFlagService {

    private final AnomalyFlagRecordRepository flagRepo;

    public AnomalyFlagServiceImpl(AnomalyFlagRecordRepository flagRepo) {
        this.flagRepo = flagRepo;
    }

    @Override
    public AnomalyFlagRecord flagAnomaly(AnomalyFlagRecord flag) {
        flag.setResolved(false);
        return flagRepo.save(flag);
    }

    @Override
    public AnomalyFlagRecord resolveFlag(Long id) {
        AnomalyFlagRecord existing = flagRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Anomaly flag not found"));
        existing.setResolved(true);
        return flagRepo.save(existing);
    }

    @Override
    public List<AnomalyFlagRecord> getFlagsByEmployee(Long employeeId) {
        return flagRepo.findByEmployee_Id(employeeId);
    }

    @Override
    public List<AnomalyFlagRecord> getFlagsByMetric(Long metricId) {
        return flagRepo.findByMetric_Id(metricId);
    }

    @Override
    public List<AnomalyFlagRecord> getAllFlags() {
        return flagRepo.findAll();
    }
}
