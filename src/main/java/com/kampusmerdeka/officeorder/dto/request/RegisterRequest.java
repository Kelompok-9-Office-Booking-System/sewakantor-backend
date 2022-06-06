package com.kampusmerdeka.officeorder.dto.request;

import com.sun.istack.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    @NotNull(message = "first name must not be null")
    @NotBlank(message = "first name must not be blank")
    private String firstName;

    @Nullable
    private String lastName;

    @NotNull(message = "email must not be null")
    @NotBlank(message = "email name must not be blank")
    @Email(message = "please provide a valid email")
    private String email;

    @NotNull(message = "password must not be null")
    @NotBlank(message = "password must not be blank")
    @Min(value = 5, message = "minimum character of password is 5")
    private String password;
}
