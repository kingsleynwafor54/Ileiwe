package com.ileiwe.ileiwe.model;

import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false)
    private String title;
    @Column(length = 1000)
    private String description;
    private String Language;
    @ElementCollection
    private List<String> imageUrl;
    private LocalDateTime dateCreated;
    private LocalDateTime datPublished;
    @UpdateTimestamp
    private LocalDateTime updateAt;
    private boolean isPublished;
    @ManyToOne
    private Instructor instructor;
    @ManyToMany
    private List<Student> students;
}
