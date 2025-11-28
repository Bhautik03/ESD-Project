package com.example.demo.timetable.repository;

import com.example.demo.timetable.entity.Domain;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DomainRepository extends JpaRepository<Domain, Long> {

    @Query("SELECT DISTINCT d FROM Domain d " +
           "LEFT JOIN FETCH d.courses c " +
           "LEFT JOIN FETCH c.courseSchedules " +
           "LEFT JOIN FETCH c.facultyMembers " +
           "WHERE d.id = :id")
    Optional<Domain> findWithDetailsById(@Param("id") Long id);
}

