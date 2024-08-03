package com.training.test.model;

import lombok.Data;

@Data
public class RestroDetailsRequest {

    private String id;
    private String name;
    private String owner;
    private String city;
    private String streetName;
    private String type;
    private String contact;
    private String zipCode;


}
