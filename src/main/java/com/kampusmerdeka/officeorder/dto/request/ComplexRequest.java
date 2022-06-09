package com.kampusmerdeka.officeorder.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ComplexRequest {
    private String name;
    private String description;
    private MultipartFile image;
}
