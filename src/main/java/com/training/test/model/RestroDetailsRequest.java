package com.training.test.model;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RestroDetailsRequest {

    private String name;
    private String username;
    private String contact;
}
