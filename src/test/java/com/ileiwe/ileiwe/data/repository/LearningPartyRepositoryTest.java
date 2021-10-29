package com.ileiwe.ileiwe.data.repository;

import com.ileiwe.ileiwe.data.model.Authority;
import com.ileiwe.ileiwe.data.model.LearningParty;
import com.ileiwe.ileiwe.data.model.Role;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Slf4j
@Sql(scripts={"/db/insert.sql"})
class LearningPartyRepositoryTest {
    @Autowired
    LearningPartyRepository learningPartyRepository;
    @BeforeEach
    void setup(){
    }
    @Test
    @Transactional
//    @Rollback(value = false)
    void createLearningPartyTest(){
        LearningParty learningUser= new LearningParty("Yomi1@gmail.com","Yomi123@gmail.com",new Authority(Role.ROLE_STUDENT));
        learningPartyRepository.save(learningUser);

        assertThat(learningUser.getId()).isNotNull();
        assertThat(learningUser.getEmail()).isEqualTo("Yomi1@gmail.com");
        assertThat(learningUser.getAuthorities().get(0).getAuthority()).isEqualTo(Role.ROLE_STUDENT);
//        assertThat(learningUser.getAuthorities().get(0)).get().isNotNull();
        log.info("After saving ->{}",learningUser);
    }
    @Test
    void createLearningPartyWithUniqueEmailsTest(){
        //create a learning party
        LearningParty user1=new LearningParty("okoro@gmail.com","1234",new Authority(Role.ROLE_STUDENT));
        //save to db
        learningPartyRepository.save(user1);
        assertThat(user1.getEmail()).isEqualTo("okoro@gmail.com");
        assertThat (user1.getId()).isNotNull();
        //create another learning party with same email
        LearningParty user2=new LearningParty("okoro@gmail.com","1234",new Authority(Role.ROLE_INSTRUCTOR));
      assertThrows(DataIntegrityViolationException.class,
              ()->learningPartyRepository.save(user2));

        //save and catch exception
        // assert that exception was thrown
    }
    @Test
    void learnPartyWithNullValuesTest(){
        // create party with null values
        LearningParty user1=new LearningParty(null,null,new Authority(Role.ROLE_STUDENT));
        //save to db
        assertThrows(ConstraintViolationException.class,
                ()->learningPartyRepository.save(user1));

    }

    @Test
    void learnPartyWithEmptyStringValuesTest(){

        assertThrows(ConstraintViolationException.class,()-> new LearningParty(
                "","",new Authority(Role.ROLE_STUDENT)
        ));

    }

}