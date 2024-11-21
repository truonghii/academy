package com.sqc.academy.service;

import com.sqc.academy.dto.employee.EmployeeSearchRequest;
import com.sqc.academy.model.Employee;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IEmployeeService {
    List<Employee> getByAttributes(EmployeeSearchRequest employeeSearchRequest);
    Optional<Employee> getById(UUID id);
    Employee save(Employee employee);
    void delete(UUID id);
}
