package com.training.test.entity;

import jakarta.persistence.*;

import lombok.Data;


@Data
@Entity
@Table(name = "restaurant_address")
public class RestaurantAddressDetails {

    @Id
    @SequenceGenerator(name = "restro_sequence", sequenceName = "restro_sequence", allocationSize = 1)
    @Column(name = "address_id")
    private int id;

    @Column(name = "street")
    private String streetName;

    @Column(name = "city")
    private String city;

    @Column(name = "pin_code")
    private int pinCode;




}
