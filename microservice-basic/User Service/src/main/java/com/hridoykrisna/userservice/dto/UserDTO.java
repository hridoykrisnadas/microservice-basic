package com.hridoykrisna.userservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.util.List;

@Data
public class UserDTO {
    private long userId;
    @NotBlank(message = "Name is Mandatory")
    private String name;
    @NotBlank(message = "Email is Mandatory")
    private String email;
    @NotBlank(message = "Phone No is Mandatory")
    private String phone;

    List<BookDTO> bookDTOS;
}
