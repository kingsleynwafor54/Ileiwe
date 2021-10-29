package com.ileiwe.ileiwe.data.model;

import com.ileiwe.ileiwe.data.repository.InstructorRepository;
import com.ileiwe.ileiwe.data.repository.LearningPartyRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.ileiwe.ileiwe.data.model.Gender.MALE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Slf4j
@Sql("/db/insert.sql")
class InstructorTest {
    @Autowired
    InstructorRepository instructorRepository;
    @Autowired
    LearningPartyRepository learningPartyRepository;
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
    @Test
    @Transactional
    void testThatOtherFieldsCanUpdateOtherFields(){
        Course course=new Course("Java");
        List<Course> crs=new ArrayList<>();
        crs.add(course);
        // create a learning party
        LearningParty user =new LearningParty("trainer@gmail.com",
                "1234pass",new Authority(Role.ROLE_INSTRUCTOR));
        //create instructor
        //map instructor with learning party
        Instructor instructor=Instructor.builder()
                .firstName("John")
                .lastName("Alao")
                .gender(MALE)
                .specialization("Java")
                .bio("AJ is a professional java developer from the University of Harvard ")
                .learningParty(user)
                .courses(crs).build();
        //save instructor
        instructorRepository.save(instructor);
        log.info("Instructor before saving->{}",instructor);
        assertThat(instructor.getId()).isNotNull();
        assertThat(instructor.getLearningParty().getId()).isNotNull();
        log.info("Instructor after saving->{}",instructor);
        Instructor savedInstructor=instructorRepository.findById(instructor.getId()).orElse(null);
        assertThat(savedInstructor).isNotNull();
        assertThat(savedInstructor.getBio()).isNotNull();
        assertThat(savedInstructor.getGender()).isNotNull();
        savedInstructor.setBio("I am java instructor");
        savedInstructor.setGender(MALE);
        instructorRepository.save(savedInstructor);
        assertThat(savedInstructor.getBio()).isNotNull();
        assertThat(savedInstructor.getGender()).isNotNull();



    }

    @Test
    @Transactional
    void findByUserNameTest(){
        LearningParty learningParty=
                learningPartyRepository.findByEmail("nuel@gmail.com");
        assertThat(learningParty).isNotNull();
        assertThat(learningParty.getEmail()).isEqualTo("nuel@gmail.com");
        log.info("Learning Party object->{}",learningParty);
    }

}