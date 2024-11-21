package com.sqc.academy.repository.impl;

import com.sqc.academy.dto.employee.EmployeeSearchRequest;
import com.sqc.academy.model.Employee;
import com.sqc.academy.repository.IEmployeeRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Repository
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmployeeRepository implements IEmployeeRepository {
    List<Employee> employees = new ArrayList<>(
            Arrays.asList(
                    new Employee(UUID.randomUUID(), "Hoang Van Hai", LocalDate.of(1990,1,15),
                            Employee.Gender.MALE, 1500000.00, "0975123542",1),
                    new Employee(UUID.randomUUID(), "Tran Thi Hoai", LocalDate.of(1985,5,2),
                            Employee.Gender.FEMALE, 1000000.00, "0967869868",2),
                    new Employee(UUID.randomUUID(), "Le Van Sy", LocalDate.of(1992, 3,10),
                            Employee.Gender.MALE, 2000000.00, "0988881110",3),
                    new Employee(UUID.randomUUID(), "Pham Duy Khanh", LocalDate.of(1988,7,5),
                            Employee.Gender.FEMALE, 2100000.00, "0986555333",4),
                    new Employee(UUID.randomUUID(), "Hoang Van Quy", LocalDate.of(2000,9,2),
                            Employee.Gender.MALE, 5000000.00, "0973388668",4)
            )
    );

    @Override
    public List<Employee> findByAttributes(EmployeeSearchRequest employeeSearchRequest) {
        return employees.stream()
                .filter(e->(employeeSearchRequest.getName() == null)
                        || e.getName().toLowerCase()
                        .contains(employeeSearchRequest.getName().toLowerCase()))
                .filter(e->(employeeSearchRequest.getDobFrom() == null || !e.getDob().isBefore(employeeSearchRequest.getDobFrom())))
                .filter(e->(employeeSearchRequest.getDobTo() == null || !e.getDob().isAfter(employeeSearchRequest.getDobTo())))
                .filter(e->(employeeSearchRequest.getGender() == null || e.getGender() == employeeSearchRequest.getGender()))
                .filter(e->(employeeSearchRequest.getPhone() == null || e.getPhone().contains(employeeSearchRequest.getPhone())))
                .filter(e->(employeeSearchRequest.getDepartmentId() == null || Objects.equals(e.getDepartmentId(), employeeSearchRequest.getDepartmentId())))
                .filter(e->{
                    if (employeeSearchRequest.getSalaryRange() == null){
                        return true;
                    }
                    return switch (employeeSearchRequest.getSalaryRange()){
                        case "lt5" ->
                                e.getSalary() < 5000000;
                        case "5 - 10" ->
                                e.getSalary() >= 5000000 && e.getSalary() < 10000000;
                        case "10 - 20" ->
                                e.getSalary() >= 10000000 && e.getSalary() <= 20000000;
                        case "gt20" ->
                                e.getSalary() > 200000000;
                        default -> true;
                    };
                })
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Employee> findById(UUID id) {
        return employees.stream()
                .filter(e->e.getId().equals(id))
                .findFirst();
    }

    @Override
    public Employee save(Employee employee) {
        return findById(employee.getId())
                .map(e->{
                    e.setName(employee.getName());
                    e.setDob(employee.getDob());
                    e.setGender(employee.getGender());
                    e.setPhone(employee.getPhone());
                    e.setDepartmentId(employee.getDepartmentId());
                    e.setSalary(employee.getSalary());

                    return e;
                })
                .orElseGet(()->{
                    employee.setId(UUID.randomUUID());
                    employees.add(employee);
                    return employee;
                });
    }

    @Override
    public void deleteById(UUID id) {
        findById(id).ifPresent(employees::remove);
    }
}
