package com.kampusmerdeka.officeorder.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Email;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerLoginRequest extends LoginRequest {
    @Email
    private String email;
}
