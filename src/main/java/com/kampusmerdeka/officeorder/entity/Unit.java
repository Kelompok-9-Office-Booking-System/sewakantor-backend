package com.kampusmerdeka.officeorder.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "units")
public class Unit extends BaseEntity{

    enum Type {
        OFFICE_ROOM,
        COWORKING,
        MEETING_ROOM,
        VIRTUAL_ROOM
    }

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "capacity")
    private Integer capacity;

    @Enumerated
    private Type type;

    @ManyToOne
    @JoinColumn(name = "building_id")
    private Building building;
}
