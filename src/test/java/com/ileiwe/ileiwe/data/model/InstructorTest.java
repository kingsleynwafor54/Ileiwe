package com.ileiwe.ileiwe.data.model;

import com.ileiwe.ileiwe.data.repository.InstructorRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import javax.validation.ConstraintViolationException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.ileiwe.ileiwe.data.model.Gender.MALE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Slf4j
@Sql(scripts={"/db/insert.sql"})
class InstructorTest {
    @Autowired
    InstructorRepository instructorRepository;
    @BeforeEach
    void beforeEach(){

    }
    @Test
    void saveInstructorAsLearningPartyTest(){
       // create a learning party
        LearningParty user =new LearningParty("trainer@gmail.com",
                "1234pass",new Authority(Role.ROLE_INSTRUCTOR));
       //create instructor
        //map instructor with learning party
        Instructor instructor=Instructor.builder()
                .firstName("John")
                .lastName("Alao")
                .learningParty(user).build();
       //save instructor
        instructorRepository.save(instructor);
        log.info("Instructor before saving->{}",instructor);
        assertThat(instructor.getId()).isNotNull();
        assertThat(instructor.getLearningParty().getId()).isNotNull();
        log.info("Instructor after saving->{}",instructor);
    }
    @Test
    void testThatFirstNameAndLastNameCannotBeNullInTheInstructor(){
        // create a learning party
        LearningParty user =new LearningParty("trainer@gmail.com",
                "1234pass",new Authority(Role.ROLE_INSTRUCTOR));
        //create instructor
        //map instructor with learning party
        Instructor instructor=Instructor.builder()
                .firstName(null)
                .lastName(null)
                .learningParty(user).build();

        assertThrows(ConstraintViolationException.class,
                ()->instructorRepository.save(instructor));
    }

    @Test
    void testThatFirstNameAndLastNameCannotBeBlankInTheInstructor(){
        // create a learning party
        LearningParty user =new LearningParty("trainer@gmail.com",
                "1234pass",new Authority(Role.ROLE_INSTRUCTOR));
        //create instructor
        //map instructor with learning party
        Instructor instructor=Instructor.builder()
                .firstName("   ")
                .lastName("    ")
                .learningParty(user).build();

        assertThrows(ConstraintViolationException.class,
                ()->instructorRepository.save(instructor));
    }


}