package com.ileiwe.ileiwe.data.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LearningParty {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(unique=true,nullable=false)
    @NotNull
    @NotBlank
    private String email;
    @Column(nullable=false)
    private String password;
    private String duration;
    private boolean enabled;
    @CreationTimestamp
    private LocalDateTime dateCreated;
    @OneToMany(cascade= CascadeType.PERSIST)
    private List<Authority> authorities;

    public LearningParty(String email, String password,Authority authority){
//        if(email.isEmpty()||password.isEmpty()){
//           throw new IllegalArgumentException("email and password is empty");
//        }
        this.email=email;
        this.password=password;
        addAuthority(authority);
        this.enabled=false;
    }

    private void addAuthority(Authority authority){
        if(this.authorities==null){
            this.authorities=new ArrayList<>();
        }
        this.authorities.add(authority);
    }
}
