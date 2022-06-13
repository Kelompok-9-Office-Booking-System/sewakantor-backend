package com.kampusmerdeka.officeorder.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue(value = "ADMIN")
@PrimaryKeyJoinColumn(name = "user_id", referencedColumnName = "id")
@Table(name = "admin")
public class UserAdmin extends User {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

}
