package com.kampusmerdeka.officeorder.dto.repsonse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UnitResponse {
    private Long id;
    private String name;
    private String description;
    private Integer capacity;
    private Double length;
    private Double width;
    private Double height;
    private Long buildingId;
    private String building;
    private List<UnitImageResponse> images;
}
