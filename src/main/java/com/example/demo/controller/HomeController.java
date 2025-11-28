package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public ResponseEntity<Map<String, Object>> home() {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Course Timetable API");
        response.put("version", "1.0");
        
        Map<String, String> endpoints = new HashMap<>();
        endpoints.put("GET /api/timetable/domains", "Get all domains");
        endpoints.put("GET /api/timetable/domains/{domainId}", "Get timetable for a specific domain");
        endpoints.put("GET /api/timetable/courses/{courseId}/students", "Get enrolled students for a course");
        
        response.put("endpoints", endpoints);
        
        return ResponseEntity.ok(response);
    }
}

