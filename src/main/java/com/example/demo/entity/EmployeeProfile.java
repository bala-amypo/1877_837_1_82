// src/main/java/com/example/demo/entity/EmployeeProfile.java
package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "employee_profiles")
@Data
public class EmployeeProfile {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String employeeId;

    private String fullName;
    @Column(unique = true, nullable = false)
    private String email;
    private String teamName;
    private String role;
    private Boolean active = true;
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "employee")
    private List<ProductivityMetricRecord> metrics;

    @PrePersist
    public void prePersist() {
        if (createdAt == null) createdAt = LocalDateTime.now();
    }
}
