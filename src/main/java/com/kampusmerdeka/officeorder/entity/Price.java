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

    public enum Type {
        HOURLY("Hour"),
        DAILY("Day"),
        WEEKLY("Week"),
        MONTHLY("Month"),
        YEARLY("Year");

        public String label;

        Type(String label) {
            this.label = label;
        }
    }

    @Enumerated
    private Type type;

    @Column(name = "price")
    private Long price;

    @ManyToOne
    @JoinColumn(name = "unit_id")
    private Unit unit;
}
