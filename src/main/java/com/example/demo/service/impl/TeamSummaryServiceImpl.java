package com.example.demo.service.impl;

import com.example.demo.model.TeamSummaryRecord;
import com.example.demo.repository.TeamSummaryRecordRepository;
import com.example.demo.repository.ProductivityMetricRecordRepository;
import com.example.demo.service.TeamSummaryService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TeamSummaryServiceImpl implements TeamSummaryService {

    private final TeamSummaryRecordRepository summaryRepo;
    private final ProductivityMetricRecordRepository metricRepo;

    public TeamSummaryServiceImpl(
            TeamSummaryRecordRepository summaryRepo,
            ProductivityMetricRecordRepository metricRepo) {
        this.summaryRepo = summaryRepo;
        this.metricRepo = metricRepo;
    }

    @Override
    public TeamSummaryRecord generateSummary(String teamName, LocalDate date) {
        TeamSummaryRecord summary = new TeamSummaryRecord();
        summary.setTeamName(teamName);
        summary.setSummaryDate(date);
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
