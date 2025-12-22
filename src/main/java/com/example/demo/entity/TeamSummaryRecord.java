// src/main/java/com/example/demo/entity/TeamSummaryRecord.java
package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "team_summary_records", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"teamName", "summaryDate"})
})
public class TeamSummaryRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String teamName;

    @Column(nullable = false)
    private LocalDate summaryDate;

    @Column(nullable = false)
    private Double avgHoursLogged;

    @Column(nullable = false)
    private Double avgTasksCompleted;

    @Column(nullable = false)
    private Double avgScore;

    @Column(nullable = false)
    private Integer anomalyCount;

    @Column(nullable = false)
    private LocalDateTime generatedAt;

    public TeamSummaryRecord() {}

    public TeamSummaryRecord(String teamName, LocalDate summaryDate,
                             Double avgHoursLogged, Double avgTasksCompleted, Double avgScore, Integer anomalyCount) {
        this.teamName = teamName;
        this.summaryDate = summaryDate;
        this.avgHoursLogged = avgHoursLogged;
        this.avgTasksCompleted = avgTasksCompleted;
        this.avgScore = avgScore;
        this.anomalyCount = anomalyCount;
        this.generatedAt = LocalDateTime.now();
    }

    // Getters and setters...
}
