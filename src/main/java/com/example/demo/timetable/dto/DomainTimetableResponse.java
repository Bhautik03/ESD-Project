package com.example.demo.timetable.dto;

import java.util.List;

public record DomainTimetableResponse(Long id, String program, List<CourseWithScheduleDto> courses) {}

