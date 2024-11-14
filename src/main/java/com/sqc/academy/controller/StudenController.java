package com.sqc.academy.controller;

import com.sqc.academy.model.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class StudenController {
    private List<Student> students = new ArrayList<>(
            Arrays.asList(
                    new Student(1, "Ho Van Trung", 9)
                    , new Student(2, "Dan truong", 8)
                    , new Student(3, "Michael Truong", 7)
            )
    );

    @GetMapping("/student")
    public List<Student> getStudents() {
        return students;
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                //return ResponseEntity.status(HttpStatus.OK).body(student);
                return ResponseEntity.ok(student);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping("/student")
    public ResponseEntity<Student> saveStudent(@RequestBody Student student) {
        student.setId((int) (Math.random() * 10000000));
        students.add(student);

        return ResponseEntity.status(HttpStatus.CREATED).body(student);
    }
}
