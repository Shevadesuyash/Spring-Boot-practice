package com.training.test.model;

import lombok.Data;

@Data
public class UserRegistrationRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String userName;
    private String password;
}
