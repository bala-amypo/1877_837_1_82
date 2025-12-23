// src/main/java/com/example/demo/entity/ProductivityMetricRecord.java
package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "productivity_metric_records", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"employee_id", "date"})
})
public class ProductivityMetricRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // FK to EmployeeProfile
    @ManyToOne(optional = false)
    @JoinColumn(name = "employee_id")
    private EmployeeProfile employee;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private Double hoursLogged;

    @Column(nullable = false)
    private Integer tasksCompleted;

    @Column(nullable = false)
    private Integer meetingsAttended;

    @Column(nullable = false)
    private Double productivityScore;

    @Lob
    private String rawDataJson;

    @Column(nullable = false)
    private LocalDateTime submittedAt;

    public ProductivityMetricRecord() {}

    public ProductivityMetricRecord(EmployeeProfile employee, LocalDate date,
                                    Double hoursLogged, Integer tasksCompleted, Integer meetingsAttended,
                                    String rawDataJson) {
        this.employee = employee;
        this.date = date;
        this.hoursLogged = hoursLogged;
        this.tasksCompleted = tasksCompleted;
        this.meetingsAttended = meetingsAttended;
        this.rawDataJson = rawDataJson;
        this.submittedAt = LocalDateTime.now();
    }

    // Getters and setters...
}
