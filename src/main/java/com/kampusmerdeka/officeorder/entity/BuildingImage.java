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
@Table(name = "building_images")
public class BuildingImage extends BaseEntity{

    @Column(name = "image_name", nullable = false, unique = true)
    private String name;

    @Column(name = "path")
    private String path;

    @ManyToOne
    @JoinColumn(name = "building_id")
    private Building building;
}
