// src/main/java/com/example/demo/controller/TeamSummaryController.java
package com.example.demo.controller;

import com.example.demo.entity.TeamSummaryRecord;
import com.example.demo.service.TeamSummaryService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/team-summaries")
public class TeamSummaryController {

    private final TeamSummaryService summaryService;

    public TeamSummaryController(TeamSummaryService summaryService) {
        this.summaryService = summaryService;
    }

    @PostMapping("/generate")
    public TeamSummaryRecord generate(@RequestParam String teamName, @RequestParam String summaryDate) {
        return summaryService.generateSummary(teamName, LocalDate.parse(summaryDate));
    }

    @GetMapping("/team/{teamName}")
    public List<TeamSummaryRecord> byTeam(@PathVariable String teamName) {
        return summaryService.getSummariesByTeam(teamName);
    }

    @GetMapping("/{id}")
    public TeamSummaryRecord get(@PathVariable Long id) {
        return summaryService.getAllSummaries().stream()
                .filter(s -> s.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new com.example.demo.exception.ResourceNotFoundException("Summary not found"));
    }

    @GetMapping
    public List<TeamSummaryRecord> all() {
        return summaryService.getAllSummaries();
    }
}
