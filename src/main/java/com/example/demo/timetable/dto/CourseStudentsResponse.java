package com.example.demo.timetable.dto;

import java.util.List;

public record CourseStudentsResponse(Long courseId, String courseName, List<StudentDto> students) {}

