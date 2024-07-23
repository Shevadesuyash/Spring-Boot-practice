package com.training.test.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Restro_address_details")

public class Restro_address_details {

    @Id

    @Column(name = "Address_id")
    private int id;

    @Column(name = "streetname")
    private String streetName;

    @Column(name = "city")
    private String city;

    @Column(name = "pincode")
    private String pincode;



}
