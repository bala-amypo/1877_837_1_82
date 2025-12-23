package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "team_summary_records")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeamSummaryRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String teamName;

    private LocalDate summaryDate;

    private Double avgHoursLogged;

    private Double avgTasksCompleted;

    private Double avgScore;

    private Integer anomalyCount;

    private LocalDateTime generatedAt;

    @PrePersist
    void onGenerate() {
        generatedAt = LocalDateTime.now();
    }
}
