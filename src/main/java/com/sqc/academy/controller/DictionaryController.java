package com.sqc.academy.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class DictionaryController {
    private final Map<String, String> dictionaryMap = Map.ofEntries(
            Map.entry("Hello", "xin chao"),
            Map.entry("apple", "qua tao"),
            Map.entry("banana", "qua chuoi"),
            Map.entry("orange", "qua cam"),
            Map.entry("lemon", "qua chanh"),
            Map.entry("melon", "qua dua"),
            Map.entry("watermelon", "dua hau"),
            Map.entry("blueberry", "viet quat")
    );
    @GetMapping("/dictionary")
    public ResponseEntity<String> dictionary(@RequestParam(defaultValue = "") String word) {
        String translation = dictionaryMap.get(word.trim().toLowerCase());

        if (translation != null) {
            return ResponseEntity.ok(translation);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found");
    }
}
