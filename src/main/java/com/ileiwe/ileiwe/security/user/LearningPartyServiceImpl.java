package com.ileiwe.ileiwe.security.user;

import com.ileiwe.ileiwe.data.repository.LearningPartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class LearningPartyServiceImpl implements UserDetailsService {

    @Autowired
    private LearningPartyRepository learningPartyRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }

}
