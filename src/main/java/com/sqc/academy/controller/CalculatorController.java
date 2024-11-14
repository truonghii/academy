package com.sqc.academy.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculatorController {
    @GetMapping("/calculator")
    public ResponseEntity<String> calculator(@RequestParam String xStr,
                                             @RequestParam String yStr,
                                             @RequestParam String operator) {
        if (xStr.isEmpty()){
            return ResponseEntity.badRequest().body("x is empty");
        } else if (yStr.isEmpty()){
            return ResponseEntity.badRequest().body("y is empty");
        } else if(!isDouble(xStr)){
            return ResponseEntity.badRequest().body("x must be numeric");
        } else if(!isDouble(yStr)){
            return ResponseEntity.badRequest().body("y must be numeric");
        }

        double x =Double.parseDouble(xStr);
        double y = Double.parseDouble(yStr);
        double result;

        switch (operator) {
            case "+" -> result = x + y;
            case "-" -> result = x - y;
            case "*" -> result = x * y;
            case "/" -> {
                if (y == 0) {
                    return ResponseEntity.badRequest().body("y is zero");
                }
                result = x / y;
            }
            default -> {
                return ResponseEntity.badRequest().body("operator is not supported");
            }
        }
        return ResponseEntity.ok("Result: " + result);
    }

    private boolean isDouble(String str){
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
