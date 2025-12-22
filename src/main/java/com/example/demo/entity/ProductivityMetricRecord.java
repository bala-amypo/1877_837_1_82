package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(
    name = "productivity_metric_records",
    uniqueConstraints = @UniqueConstraint(columnNames = {"employee_id", "date"})
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductivityMetricRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "employee_id")
    private Long employeeId;

    private LocalDate date;

    private Double hoursLogged;

    private Integer tasksCompleted;

    private Integer meetingsAttended;

    private Double productivityScore;

    @Column(columnDefinition = "TEXT")
    private String rawDataJson;

    private LocalDateTime submittedAt;

    @PrePersist
    void onSubmit() {
        submittedAt = LocalDateTime.now();
    }
}
