package com.ileiwe.ileiwe.data.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@AllArgsConstructor
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @NotNull
    @Column(nullable = false)
    private String firstName;
    @Column(nullable=false)
    @NotBlank
    @NotNull
    private String lastName;
    @Enumerated(EnumType.STRING)
    private Gender gender;
//    @NotNull
//    @NotBlank
    private String specialization;
    @Column(length=1000)
    private String bio;
    @OneToOne(cascade=CascadeType.PERSIST)
    @JoinColumn
    private LearningParty learningParty;
    @OneToMany
    private List<Course> courses;
}
