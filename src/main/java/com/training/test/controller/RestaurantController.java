package com.training.test.controller;

import com.training.test.model.RestroDetailsRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restro")
public class RestaurantController {

    @GetMapping("/new")
    public ResponseEntity<String> getRestaurants() {
        return new ResponseEntity<>("This is a first GET request",HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<String> setResto(@RequestBody RestroDetailsRequest restroDetailsRequest) {

        return new ResponseEntity<>("Restaurant name is " + restroDetailsRequest.getName()+" AND user is " + restroDetailsRequest.getUsername(),HttpStatus.ACCEPTED);
    }

}
