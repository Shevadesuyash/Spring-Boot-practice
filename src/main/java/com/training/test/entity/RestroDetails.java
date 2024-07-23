package com.training.test.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "Restro_details")
public class RestroDetails {

    @Id
    private int id;

    @Column(name = "Restro_name")
    private String name;

    @Column(name = "owner_name")
    private String owner_name;

    @OneToOne
    @PrimaryKeyJoinColumn
    @JoinColumn(name = "address", referencedColumnName = "address_id")
    private Restro_address_details addressDetails;

    @Column(name = "type")
    private String type;

    @Column(name = "contact_details")
    private String contact_details;
}
