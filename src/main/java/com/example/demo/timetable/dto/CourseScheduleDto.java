package com.example.demo.timetable.dto;

import java.time.LocalTime;

public record CourseScheduleDto(String day, LocalTime time, String room, String building) {}

