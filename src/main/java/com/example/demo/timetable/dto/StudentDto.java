package com.example.demo.timetable.dto;

public record StudentDto(
                Long id,
                String rollNumber,
                String firstName,
                String lastName,
                String email,
                Double cgpa,
                Integer totalCredits,
                Integer graduationYear) {
}
