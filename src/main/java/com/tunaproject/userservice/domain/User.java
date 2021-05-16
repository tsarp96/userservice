package com.tunaproject.userservice.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private String id;
    private String name;
    private String surname;
    private String email;
    private String password;
}
