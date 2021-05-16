package com.tunaproject.userservice.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequest {
    private String username;
    private String name;
    private String surname;
    private String password;
    private String email;
}
