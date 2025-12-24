// src/main/java/com/example/demo/service/TeamSummaryService.java
package com.example.demo.service;

import com.example.demo.entity.TeamSummaryRecord;

import java.time.LocalDate;
import java.util.List;

public interface TeamSummaryService {
    TeamSummaryRecord generateSummary(String teamName, LocalDate summaryDate);
    List<TeamSummaryRecord> getSummariesByTeam(String teamName);
    List<TeamSummaryRecord> getAllSummaries();
}
