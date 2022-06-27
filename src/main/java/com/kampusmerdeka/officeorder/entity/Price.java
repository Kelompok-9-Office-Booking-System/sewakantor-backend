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
@Table(name = "prices")
public class Price extends BaseEntity {

    enum Type {
        HOURLY,
        DAILY,
        WEEKLY,
        MONTHLY,
        YEARLY
    }

    @Enumerated
    @Column(name = "price_type")
    private Type type;

    @Column(name = "price")
    private Long price;

    @ManyToOne
    @JoinColumn(name = "unit_id")
    private Unit unit;
}
