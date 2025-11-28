package com.example.demo.timetable.service;

import com.example.demo.timetable.dto.CourseScheduleDto;
import com.example.demo.timetable.dto.CourseStudentsResponse;
import com.example.demo.timetable.dto.CourseWithScheduleDto;
import com.example.demo.timetable.dto.DomainSummary;
import com.example.demo.timetable.dto.DomainTimetableResponse;
import com.example.demo.timetable.dto.FacultyDto;
import com.example.demo.timetable.dto.StudentDto;
import com.example.demo.timetable.entity.Course;
import com.example.demo.timetable.entity.CourseSchedule;
import com.example.demo.timetable.entity.Domain;
import com.example.demo.timetable.entity.Employee;
import com.example.demo.timetable.entity.Student;
import com.example.demo.timetable.exception.ResourceNotFoundException;
import com.example.demo.timetable.repository.CourseRepository;
import com.example.demo.timetable.repository.DomainRepository;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TimetableService {

    private final DomainRepository domainRepository;
    private final CourseRepository courseRepository;

    public List<DomainSummary> getAllDomains() {
        return domainRepository.findAll(Sort.by("program"))
                .stream()
                .map(domain -> new DomainSummary(domain.getId(), domain.getProgram()))
                .toList();
    }

    public DomainTimetableResponse getDomainTimetable(Long domainId) {
        Domain domain =
                domainRepository
                        .findWithDetailsById(domainId)
                        .orElseThrow(() -> new ResourceNotFoundException("Domain", domainId));

        List<CourseWithScheduleDto> courses = domain.getCourses().stream()
                .sorted(Comparator.comparing(Course::getName))
                .map(this::mapCourseToDto)
                .toList();

        return new DomainTimetableResponse(domain.getId(), domain.getProgram(), courses);
    }

    public CourseStudentsResponse getCourseStudents(Long courseId) {
        Course course =
                courseRepository
                        .findWithStudentsById(courseId)
                        .orElseThrow(() -> new ResourceNotFoundException("Course", courseId));

        List<StudentDto> students = course.getStudents().stream()
                .sorted(Comparator.comparing(Student::getRollNumber))
                .map(student -> new StudentDto(
                        student.getId(),
                        student.getRollNumber(),
                        student.getFirstName(),
                        student.getLastName(),
                        student.getEmail()))
                .toList();

        return new CourseStudentsResponse(course.getId(), course.getName(), students);
    }

    private CourseWithScheduleDto mapCourseToDto(Course course) {
        List<CourseScheduleDto> schedule = course.getCourseSchedules().stream()
                .sorted(Comparator.comparing(CourseSchedule::getDay).thenComparing(CourseSchedule::getTime))
                .map(slot -> new CourseScheduleDto(
                        slot.getDay(), slot.getTime(), slot.getRoom(), slot.getBuilding()))
                .toList();

        List<FacultyDto> faculty = course.getFacultyMembers().stream()
                .sorted(Comparator.comparing(Employee::getLastName).thenComparing(Employee::getFirstName))
                .map(member -> new FacultyDto(
                        member.getId(),
                        member.getFirstName(),
                        member.getLastName(),
                        member.getEmail()))
                .collect(Collectors.toList());

        return new CourseWithScheduleDto(
                course.getId(), course.getName(), course.getCourseCode(), schedule, faculty);
    }
}
