package com.kampusmerdeka.officeorder.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "user_type")
@Table(name = "users")
public abstract class User extends BaseEntity {
    public enum Role {
        SUPERADMIN,
        SUPERVISOR,
        CONSULTANT,
        CUSTOMER
    }

    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated
    @Column(name = "role", nullable = false)
    private Role role;

    @Column(name = "avatar")
    private String avatar;
}
