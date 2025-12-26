package org.cna.user.service.userservice.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "ID")
    private String userID;
    @Column(name = "NAME")
    private String userName;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "ABOUT")
    private String about;
    @Transient
    private List<Rating> ratings=new ArrayList<>();
}
