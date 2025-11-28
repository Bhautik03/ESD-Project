package com.example.demo.timetable.dto;

import java.util.List;

public record CourseWithScheduleDto(
        Long id,
        String name,
        String courseCode,
        List<CourseScheduleDto> schedule,
        List<FacultyDto> faculty) {}

