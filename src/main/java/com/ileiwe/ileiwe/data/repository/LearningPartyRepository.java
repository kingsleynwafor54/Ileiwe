package com.ileiwe.ileiwe.data.repository;

import com.ileiwe.ileiwe.data.model.LearningParty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

@Repository
public interface LearningPartyRepository extends JpaRepository<LearningParty, Long> {

    LearningParty findByEmail(String email);
//    @Query("select '*' from LearningParty "+ "as L where L.email=:email")
//    LearningParty findUserByEmail(String email);

}
