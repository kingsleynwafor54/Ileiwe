package com.ileiwe.ileiwe.data.repository;

import com.ileiwe.ileiwe.data.model.LearningParty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;


public interface LearningPartyRepository extends JpaRepository<LearningParty, Long> {

}
