package com.sqc.academy.service.imlpl;

import com.sqc.academy.dto.employee.EmployeeSearchRequest;
import com.sqc.academy.model.Employee;
import com.sqc.academy.repository.IEmployeeRepository;
import com.sqc.academy.service.IEmployeeService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmployeeService implements IEmployeeService {
    IEmployeeRepository employeeRepository;

    @Override
    public List<Employee> getByAttributes(EmployeeSearchRequest employeeSearchRequest) {
        return employeeRepository.findByAttributes(employeeSearchRequest);
    }

    @Override
    public Optional<Employee> getById(UUID id) {
        return employeeRepository.findById(id);
    }

    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public void delete(UUID id) {
        employeeRepository.deleteById(id);
    }
}
