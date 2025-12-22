// src/main/java/com/example/demo/repository/TeamSummaryRecordRepository.java
package com.example.demo.repository;

import com.example.demo.entity.TeamSummaryRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TeamSummaryRecordRepository extends JpaRepository<TeamSummaryRecord, Long> {
    List<TeamSummaryRecord> findByTeamName(String teamName);
    Optional<TeamSummaryRecord> findByTeamNameAndSummaryDate(String teamName, LocalDate summaryDate);
}
