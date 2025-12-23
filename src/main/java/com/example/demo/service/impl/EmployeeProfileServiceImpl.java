// src/main/java/com/example/demo/service/impl/EmployeeProfileServiceImpl.java
package com.example.demo.service.impl;

import com.example.demo.entity.EmployeeProfile;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.EmployeeProfileRepository;
import com.example.demo.service.EmployeeProfileService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeProfileServiceImpl implements EmployeeProfileService {

    private final EmployeeProfileRepository employeeRepo;

    public EmployeeProfileServiceImpl(EmployeeProfileRepository employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    @Override
    public EmployeeProfile createEmployee(EmployeeProfile employee) {
        return employeeRepo.save(employee);
    }

    @Override
    public EmployeeProfile getEmployeeById(Long id) {
        return employeeRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
    }

    @Override
    public List<EmployeeProfile> getAllEmployees() {
        return employeeRepo.findAll();
    }

    @Override
    public Optional<EmployeeProfile> findByEmployeeId(String employeeId) {
        return employeeRepo.findByEmployeeId(employeeId);
    }

    @Override
    public EmployeeProfile updateEmployeeStatus(Long id, boolean active) {
        EmployeeProfile emp = getEmployeeById(id);
        emp.setActive(active);
        return employeeRepo.save(emp);
    }
}
