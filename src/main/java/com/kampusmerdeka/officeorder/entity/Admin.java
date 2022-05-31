package com.kampusmerdeka.officeorder.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "admin")
public class Admin extends BaseEntity {
    public enum Role {
        SUPERADMIN,
        SUPERVISOR,
        CONSULTANT,
        CUSTOMER
    }

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated
    private Role role;
}
