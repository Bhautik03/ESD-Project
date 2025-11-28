package com.example.demo.timetable.repository;

import com.example.demo.timetable.entity.Course;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CourseRepository extends JpaRepository<Course, Long> {

    @EntityGraph(attributePaths = {"students", "courseSchedules", "facultyMembers"})
    @Query("SELECT c FROM Course c WHERE c.id = :id")
    Optional<Course> findWithStudentsById(@Param("id") Long id);
}

