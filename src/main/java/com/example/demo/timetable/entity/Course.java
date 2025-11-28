package com.example.demo.timetable.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Courses")
@Getter
@Setter
@NoArgsConstructor
public class Course {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "course_id")
        private Long id;

        @Column(nullable = false)
        private String name;

        @Column(name = "course_code", nullable = false)
        private String courseCode;

        @Column(name = "description")
        private String description;

        @Column(name = "year")
        private Integer year;

        @Column(name = "term")
        private String term;

        @Column(name = "credits")
        private Integer credits;

        @Column(name = "capacity")
        private Integer capacity;

        @ManyToMany
        @JoinTable(name = "Course_Domain", joinColumns = @JoinColumn(name = "course_id"), inverseJoinColumns = @JoinColumn(name = "domain_id"))
        private Set<Domain> domains = new LinkedHashSet<>();

        @ManyToMany
        @JoinTable(name = "Faculty_Courses", joinColumns = @JoinColumn(name = "course_id"), inverseJoinColumns = @JoinColumn(name = "faculty"))
        private Set<Employee> facultyMembers = new LinkedHashSet<>();

        @ManyToMany
        @JoinTable(name = "Student_Courses", joinColumns = @JoinColumn(name = "course_id"), inverseJoinColumns = @JoinColumn(name = "student_id"))
        private Set<Student> students = new LinkedHashSet<>();

        @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
        private List<CourseSchedule> courseSchedules = new ArrayList<>();
}
