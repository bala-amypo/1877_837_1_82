// src/main/java/com/example/demo/controller/EmployeeProfileController.java
package com.example.demo.controller;

import com.example.demo.dto.EmployeeProfileDto;
import com.example.demo.entity.EmployeeProfile;
import com.example.demo.service.EmployeeProfileService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeProfileController {

    private final EmployeeProfileService employeeService;

    public EmployeeProfileController(EmployeeProfileService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public EmployeeProfileDto create(@RequestBody EmployeeProfileDto dto) {
        EmployeeProfile entity = new EmployeeProfile(dto.getEmployeeId(), dto.getFullName(), dto.getEmail(),
                dto.getTeamName(), dto.getRole(), dto.getActive() != null ? dto.getActive() : true);
        EmployeeProfile saved = employeeService.createEmployee(entity);
        return toDto(saved);
    }

    @GetMapping("/{id}")
    public EmployeeProfileDto getById(@PathVariable Long id) {
        return toDto(employeeService.getEmployeeById(id));
    }

    @GetMapping
    public List<EmployeeProfile> list() {
        return employeeService.getAllEmployees();
    }

    @PutMapping("/{id}/status")
    public EmployeeProfileDto updateStatus(@PathVariable Long id, @RequestParam boolean active) {
        return toDto(employeeService.updateEmployeeStatus(id, active));
    }

    @GetMapping("/lookup/{employeeId}")
    public EmployeeProfileDto lookup(@PathVariable String employeeId) {
        EmployeeProfile emp = employeeService.findByEmployeeId(employeeId)
                .orElseThrow(() -> new com.example.demo.exception.ResourceNotFoundException("Employee not found"));
        return toDto(emp);
    }

    private EmployeeProfileDto toDto(EmployeeProfile e) {
        EmployeeProfileDto dto = new EmployeeProfileDto();
        BeanUtils.copyProperties(e, dto);
        dto.setId(e.getId());
        dto.setCreatedAt(e.getCreatedAt());
        return dto;
    }
}
