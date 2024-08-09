package com.training.test.model;

import lombok.Data;

@Data
public class RestroOnlineRequest {

    private String name;
    private String owner;
    private String city;
    private String streetName;
    private String type;
    private String contact;
    private String zipCode;
    private String email;
}
