package com.sqc.academy.repository;

import com.sqc.academy.dto.employee.EmployeeSearchRequest;
import com.sqc.academy.model.Employee;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IEmployeeRepository {
    List<Employee> findByAttributes(EmployeeSearchRequest employeeSearchRequest);

    Optional<Employee> findById(UUID id);

    Employee save(Employee employee);

    void deleteById(UUID id);
}
