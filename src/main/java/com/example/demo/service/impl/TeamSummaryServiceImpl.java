// src/main/java/com/example/demo/service/impl/TeamSummaryServiceImpl.java
package com.example.demo.service.impl;

import com.example.demo.entity.AnomalyFlagRecord;
import com.example.demo.entity.ProductivityMetricRecord;
import com.example.demo.entity.TeamSummaryRecord;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.AnomalyFlagRecordRepository;
import com.example.demo.repository.ProductivityMetricRecordRepository;
import com.example.demo.repository.TeamSummaryRecordRepository;
import com.example.demo.service.TeamSummaryService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.DoubleSummaryStatistics;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamSummaryServiceImpl implements TeamSummaryService {

    private final TeamSummaryRecordRepository summaryRepo;
    private final ProductivityMetricRecordRepository metricRepo;
    private final AnomalyFlagRecordRepository flagRepo;

    public TeamSummaryServiceImpl(
            TeamSummaryRecordRepository summaryRepo,
            ProductivityMetricRecordRepository metricRepo,
            AnomalyFlagRecordRepository flagRepo
    ) {
        this.summaryRepo = summaryRepo;
        this.metricRepo = metricRepo;
        this.flagRepo = flagRepo;
    }

    @Override
    public TeamSummaryRecord generateSummary(String teamName, LocalDate summaryDate) {
        // uniqueness enforcement
        summaryRepo.findByTeamNameAndSummaryDate(teamName, summaryDate).ifPresent(s -> {
            throw new IllegalStateException("Summary already exists for team and date");
        });

        // Collect metrics for team on date
        List<ProductivityMetricRecord> metricsForTeamOnDate = metricRepo.findAll().stream()
                .filter(m -> m.getEmployee() != null
                        && teamName.equals(m.getEmployee().getTeamName())
                        && summaryDate.equals(m.getDate()))
                .collect(Collectors.toList());

        DoubleSummaryStatistics hoursStats = metricsForTeamOnDate.stream().mapToDouble(ProductivityMetricRecord::getHoursLogged).summaryStatistics();
        IntSummaryStatistics taskStats = metricsForTeamOnDate.stream().mapToInt(ProductivityMetricRecord::getTasksCompleted).summaryStatistics();
        DoubleSummaryStatistics scoreStats = metricsForTeamOnDate.stream().mapToDouble(ProductivityMetricRecord::getProductivityScore).summaryStatistics();

        int anomalyCount = (int) flagRepo.findAll().stream()
                .filter(f -> f.getEmployee() != null
                        && teamName.equals(f.getEmployee().getTeamName())
                        && f.getMetric() != null
                        && summaryDate.equals(f.getMetric().getDate()))
                .count();

        TeamSummaryRecord summary = new TeamSummaryRecord(
                teamName,
                summaryDate,
                metricsForTeamOnDate.isEmpty() ? 0.0 : hoursStats.getAverage(),
                metricsForTeamOnDate.isEmpty() ? 0.0 : taskStats.getAverage(),
                metricsForTeamOnDate.isEmpty() ? 0.0 : scoreStats.getAverage(),
                anomalyCount
        );

        return summaryRepo.save(summary);
    }

    @Override
    public List<TeamSummaryRecord> getSummariesByTeam(String teamName) {
        return summaryRepo.findByTeamName(teamName);
    }

    @Override
    public List<TeamSummaryRecord> getAllSummaries() {
        return summaryRepo.findAll();
    }
}
