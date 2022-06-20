package com.kampusmerdeka.officeorder.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UnitRequest {
    @NotNull(message = "name must not be null")
    @NotBlank(message = "name must not be empty")
    private String name;

    @NotNull(message = "description must not be null")
    @NotBlank(message = "description must not be empty")
    private String description;

    @NotNull(message = "building id must not be null")
    @Min(value = 0, message = "invalid value of building id")
    private Long buildingId;

    private Integer capacity;

    private Double length;

    private Double width;

    private Double height;

    private List<MultipartFile> images;
}
