// src/main/java/com/example/demo/dto/EmployeeProfileDto.java
package com.example.demo.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class EmployeeProfileDto {
    private Long id;
    private String employeeId;
    private String fullName;
    private String email;
    private String teamName;
    private String role;
    private Boolean active;
    private LocalDateTime createdAt;
}
