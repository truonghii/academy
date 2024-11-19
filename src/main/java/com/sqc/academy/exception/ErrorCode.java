package com.sqc.academy.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum ErrorCode {
   EMPLOYEE_NOT_EXISTED(40401,"Department is not existed", HttpStatus.NOT_FOUND),
    DEPARTMENT_NOT_EXISTED(40401,"Department is not existed", HttpStatus.NOT_FOUND);

    int code;
    String message;
    HttpStatus httpStatus;
}
