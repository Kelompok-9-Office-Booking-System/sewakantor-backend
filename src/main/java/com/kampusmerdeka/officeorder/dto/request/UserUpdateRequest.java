package com.kampusmerdeka.officeorder.dto.request;

import com.kampusmerdeka.officeorder.entity.User;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserUpdateRequest {
    @NotNull(message = "name is requuired")
    @NotBlank(message = "name is requuired")
    private String name;

    @NotNull(message = "username is requuired")
    @NotBlank(message = "username is requuired")
    private String username;

    @NotNull(message = "role is requuired")
    @NotBlank(message = "role is requuired")
    @ValueOfEnum(enumClass = User.Role.class, message = "role not valid")
    private String role;

    private String password;

    private MultipartFile avatar;
}
