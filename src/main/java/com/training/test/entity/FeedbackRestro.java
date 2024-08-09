package com.training.test.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "restro_feedback")
public class FeedbackRestro {

    @Id
    @SequenceGenerator(name = "restro_feedback_sequence",sequenceName ="restro_feedback_sequence",allocationSize= 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="restro_feedback_sequence")
    @Column(name = "feedback_id")
    private int id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "email")
    private String email;

    @ManyToOne
    @JoinColumn(name = "restro_id", referencedColumnName = "id")
    private RestroDetails restroDetails;

    @Column(name = "message")
    private String message;

    @Column(name = "rating")
    private int rating;


}
