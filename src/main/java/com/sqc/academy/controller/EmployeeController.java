package com.sqc.academy.controller;

import com.sqc.academy.dto.employee.EmployeeSearchRequest;
import com.sqc.academy.response.JsonResponse;
import com.sqc.academy.exception.AppException;
import com.sqc.academy.exception.ErrorCode;
import com.sqc.academy.model.Employee;
import com.sqc.academy.service.IEmployeeService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/employees")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmployeeController {
    IEmployeeService employeeService;

    @GetMapping
    public ResponseEntity<?> getAllEmployees(EmployeeSearchRequest employeeSearchRequest) {
        return JsonResponse.ok(employeeService.getByAttributes(employeeSearchRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable UUID id) {
        return employeeService.getById(id)
                .map(JsonResponse::ok)
                .orElseThrow(()->new AppException(ErrorCode.EMPLOYEE_NOT_EXISTED));
    }

    @PostMapping
    public ResponseEntity<?> createEmployee(@RequestBody Employee employee) {
        return JsonResponse.created(employeeService.save(employee));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable UUID id, @RequestBody Employee employee) {
        employeeService.getById(id)
                .orElseThrow(()-> new AppException(ErrorCode.EMPLOYEE_NOT_EXISTED));
        employee.setId(id);
        return JsonResponse.ok(employeeService.save(employee));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable UUID id) {
        employeeService.getById(id)
                .orElseThrow(()-> new AppException(ErrorCode.EMPLOYEE_NOT_EXISTED));
        employeeService.delete(id);
        return JsonResponse.noContent();
    }
}
