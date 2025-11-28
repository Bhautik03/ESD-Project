package com.example.demo.timetable.controller;

import com.example.demo.timetable.dto.CourseStudentsResponse;
import com.example.demo.timetable.dto.DomainSummary;
import com.example.demo.timetable.dto.DomainTimetableResponse;
import com.example.demo.timetable.service.TimetableService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/timetable")
@RequiredArgsConstructor
public class TimetableController {

    private final TimetableService timetableService;

    @GetMapping("/domains")
    public List<DomainSummary> getDomains() {
        return timetableService.getAllDomains();
    }

    @GetMapping("/domains/{domainId}")
    public DomainTimetableResponse getDomainTimetable(@PathVariable Long domainId) {
        return timetableService.getDomainTimetable(domainId);
    }

    @GetMapping("/courses/{courseId}/students")
    public CourseStudentsResponse getCourseStudents(@PathVariable Long courseId) {
        return timetableService.getCourseStudents(courseId);
    }
}

