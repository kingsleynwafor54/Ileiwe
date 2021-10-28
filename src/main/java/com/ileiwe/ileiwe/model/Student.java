package com.ileiwe.ileiwe.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDateTime dob;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @OneToOne
    private LearningParty learningParty;
    @ManyToMany
    private List<Course> course;
}
