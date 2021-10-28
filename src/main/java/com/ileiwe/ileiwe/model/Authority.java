package com.ileiwe.ileiwe.model;

import javax.persistence.*;
import java.util.UUID;
@Entity
public class Authority {
    @Id
    @GeneratedValue
    private UUID id;
    @ManyToOne
    private LearningParty user;
    @Enumerated(EnumType.STRING)
    private Role authority;

}
