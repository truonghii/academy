package com.sqc.academy.exception;

import com.sqc.academy.dto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class GlobalExceptionHandler {
    @ExceptionHandler(AppException.class)
    ResponseEntity<?> handleAppException(AppException e) {
        ErrorCode errorCode = e.getErrorCode();

                ApiResponse.builder()
                    .code(errorCode.getCode())
                    .message(errorCode.getMessage())
                    .build()
        );
    }
}
