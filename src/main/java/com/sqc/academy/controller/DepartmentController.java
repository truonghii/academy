package com.sqc.academy.controller;

import com.sqc.academy.response.JsonResponse;
import com.sqc.academy.exception.AppException;
import com.sqc.academy.exception.ErrorCode;
import com.sqc.academy.model.Department;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private final List<Department> departments = new ArrayList<>(
            Arrays.asList(
                    new Department(1, "quan ly"),
                    new Department(2, "Ke toan"),
                    new Department(3, "Sale-Marketing"),
                    new Department(4, "San xuat")
            )
    );

    @GetMapping
    public ResponseEntity<?> getAll(){
        return JsonResponse.ok(departments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable int id){
        return departments.stream()
                .filter(d->d.getId() == id)
                .findFirst()
                .map(JsonResponse::ok)
                .orElseThrow(()-> new AppException((ErrorCode.DEPARTMENT_NOT_EXISTED)));
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> create(@RequestBody Department department){
        department.setId((int) (Math.random()*1000000));
        departments.add(department);
        return JsonResponse.created(departments);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody Department department){
        return departments.stream()
                .filter(d->d.getId() == id)
                .findFirst()
                .map(d->{
                    d.setName(department.getName());
                    return JsonResponse.ok(d);
                })
                .orElseThrow(()-> new AppException((ErrorCode.DEPARTMENT_NOT_EXISTED)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id){
        return departments.stream()
                .filter(d->d.getId() == id)
                .findFirst()
                .map(d->{
                    departments.remove(d);
                    return JsonResponse.noContent();
                })
                .orElseThrow(()-> new AppException((ErrorCode.DEPARTMENT_NOT_EXISTED)));
    }
}
