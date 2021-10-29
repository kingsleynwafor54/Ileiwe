package com.ileiwe.ileiwe.data.repository;

import com.ileiwe.ileiwe.data.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestMapping;


public interface InstructorRepository extends JpaRepository<Instructor,Long>{
}
