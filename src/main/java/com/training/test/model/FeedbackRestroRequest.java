package com.training.test.model;

import lombok.Data;

@Data
public class FeedbackRestroRequest {

    private String userName;
    private String email;
    private String message;
    private int restroId;
    private int rating;

}
