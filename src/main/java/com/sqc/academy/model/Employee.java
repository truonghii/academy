package com.sqc.academy.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Employee {
    private UUID id;
    private String name;
    private LocalDate dob;
    private Gender gender;
    private Double salary;
    private String phone;


    public enum Gender {
        MALE,
        FEMALE,
        OTHER,
    }
}
