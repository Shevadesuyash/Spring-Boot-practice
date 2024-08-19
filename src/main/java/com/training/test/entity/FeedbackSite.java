package com.training.test.entity;


import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name = "site_feedback")
public class FeedbackSite {

    @Id
    @SequenceGenerator(name = "site_feedback_sequence",sequenceName ="site_feedback_sequence",allocationSize= 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="site_feedback_sequence")
    @Column(name = "site_feedback_id")
    private int id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "email")
    private String email;

    @Column(name = "massage")
    private String massage;
}
