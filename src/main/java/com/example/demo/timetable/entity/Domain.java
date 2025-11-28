package com.example.demo.timetable.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.LinkedHashSet;
import java.util.Set;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Domains")
@Getter
@Setter
@NoArgsConstructor
public class Domain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "domain_id")
    private Long id;

    @Column(nullable = false)
    private String program;

    @Column(name = "batch")
    private String batch;

    @Column(name = "capacity")
    private Integer capacity;

    @Column(name = "qualification")
    private String qualification;

    @ManyToMany(mappedBy = "domains")
    private Set<Course> courses = new LinkedHashSet<>();
}
