package com.kampusmerdeka.officeorder.dto.repsonse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpaceResponse {
    private Long id;
    private String building;
    private String address;
    private Integer unit;
    private Double review;
    private Long price;
    private String image;
}
