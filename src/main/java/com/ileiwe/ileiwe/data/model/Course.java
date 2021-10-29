package com.ileiwe.ileiwe.data.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
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

    public Course(String title){
        this.title=title;
    }
}
