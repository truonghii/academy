package com.sqc.academy.model;

import java.time.LocalDate;
import java.util.UUID;

public class Employee {
    private UUID id;
    private String name;
    private LocalDate dob;
    private Gender gender;
    private Double salary;
    private String phone;

    public Employee() {

    }
    public Employee(UUID id,String name, LocalDate dob, Gender gender, Double salary, String phone) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.salary = salary;
        this.phone = phone;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public enum Gender {
        MALE,
        FEMALE,
        OTHER;
    }
}
