package com.training.test.entity;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name = "restaurant_address")
public class RestaurantAddressDetails {

    @Id
    @SequenceGenerator(name = "restroa_address_Sequence", sequenceName = "restroa_address_Sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "restroa_address_Sequence")
    @Column(name = "address_id")
    private int id;

    @Column(name = "street")
    private String streetName;

    @Column(name = "city")
    private String city;

    @Column(name = "pin_code")
    private int pinCode;

}
