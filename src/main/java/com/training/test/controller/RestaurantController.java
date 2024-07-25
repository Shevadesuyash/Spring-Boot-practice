package com.training.test.controller;

import com.training.test.entity.RestroDetails;
import com.training.test.model.RestroDetailsRequest;
import com.training.test.service.LoginService;
import com.training.test.service.RestroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restro")
public class RestaurantController {


    //    @Autowired
    RestroService restroService;

    //    @Autowired
    LoginService loginService;

    RestaurantController(RestroService restroService, LoginService loginService) {
        this.restroService = restroService;
        this.loginService = loginService;
    }

    @GetMapping("/new")
    public ResponseEntity<String> getRestaurants() {
        return new ResponseEntity<>("This is a first GET request", HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<String> setResto(@RequestBody RestroDetailsRequest restroDetailsRequest) {
        this.restroService.processNewRestro(restroDetailsRequest);

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


    // 24 jun
    //----------------------------------------------------------------

    @GetMapping("/getDetails")
    public RestroDetails getRestro() {
        return this.restroService.getRestro();

    }

    @PostMapping("/updateDetails")
    public RestroDetails updateDetails() {
        return this.restroService.updateRestro();



    }

}