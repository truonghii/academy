package com.sqc.academy.controller;

import com.sqc.academy.Response.JsonResponse;
import com.sqc.academy.exception.AppException;
import com.sqc.academy.exception.ErrorCode;
import com.sqc.academy.model.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final List<Employee> employees = new ArrayList<>(
            Arrays.asList(
                    new Employee(UUID.randomUUID(), "Hoang Van Hai", LocalDate.of(1990,1,15),
                            Employee.Gender.MALE, 1500000.00, "0975123542"),
                    new Employee(UUID.randomUUID(), "Tran Thi Hoai", LocalDate.of(1985,5,2),
                            Employee.Gender.FEMALE, 1000000.00, "0967869868"),
                    new Employee(UUID.randomUUID(), "Le Van Sy", LocalDate.of(1992, 3,10),
                            Employee.Gender.MALE, 2000000.00, "0988881110"),
                    new Employee(UUID.randomUUID(), "Pham Duy Khanh", LocalDate.of(1988,7,5),
                            Employee.Gender.FEMALE, 2100000.00, "0986555333"),
                    new Employee(UUID.randomUUID(), "Hoang Van Quy", LocalDate.of(2000,9,2),
                            Employee.Gender.MALE, 5000000.00, "0973388668")
            )
    );

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable UUID id) {
        return employees.stream()
                .filter(e->e.getId().equals(id))
                .findFirst()
                .map(JsonResponse::ok)
                .orElseThrow(() -> new AppException(ErrorCode.EMPLOYEE_NOT_EXISTED));
    }

    @PostMapping
    public ResponseEntity<?> createEmployee(@RequestBody Employee employee) {
        employee.setId(UUID.randomUUID());
        employees.add(employee);
        return JsonResponse.created(employee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable UUID id, @RequestBody Employee employee) {
        return employees.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .map(e -> {
                    e.setName(employee.getName());
                    e.setGender(employee.getGender());
                    e.setSalary(employee.getSalary());
                    e.setDob(employee.getDob());
                    e.setPhone(employee.getPhone());

                    return JsonResponse.ok(e);
                })
                .orElseThrow(()-> new AppException(ErrorCode.EMPLOYEE_NOT_EXISTED));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable UUID id) {
        return employees.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .map(s -> {
                    employees.remove(s);
                    return JsonResponse.noContent();
                })
                .orElseThrow(()-> new AppException(ErrorCode.EMPLOYEE_NOT_EXISTED));
    }
}
