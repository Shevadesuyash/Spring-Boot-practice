package com.training.test.model;

import lombok.Data;

@Data
public class ForgotPassword {

    private String userName;
    private String email;
    private Long phone;

}
