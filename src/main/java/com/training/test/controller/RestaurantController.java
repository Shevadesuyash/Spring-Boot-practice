package com.training.test.controller;

import com.training.test.model.RestroDetailsRequest;
import com.training.test.service.RestroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restro")
public class RestaurantController {

    @Autowired
    RestroService restroservice;


    @GetMapping("/new")
    public ResponseEntity<String> getRestaurants() {
        return new ResponseEntity<>("This is a first GET request", HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<String> setResto(@RequestBody RestroDetailsRequest restroDetailsRequest) {

        this.restroservice.processNewRestro(restroDetailsRequest);

        return new ResponseEntity<>("Restaurant create by name is " + restroDetailsRequest.getName() + " AND user is " + restroDetailsRequest.getUsername(), HttpStatus.ACCEPTED);
    }

    @PostMapping("/delete")
    public ResponseEntity<String> deleteResto(@RequestBody RestroDetailsRequest restroDetailsRequest) {
        return new ResponseEntity<>("Restaurant delete by name is " + restroDetailsRequest.getName() + " AND user is " + restroDetailsRequest.getUsername(), HttpStatus.ACCEPTED);
    }

    @PostMapping("/update")
    public ResponseEntity<String> update(@RequestBody RestroDetailsRequest restroDetailsRequest) {
        return new ResponseEntity<>("Restaurant updated name is " + restroDetailsRequest.getName() + " AND  new name is " + restroDetailsRequest.getUsername(), HttpStatus.ACCEPTED);
    }

}