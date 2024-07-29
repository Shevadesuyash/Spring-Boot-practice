package com.training.test.controller;

import com.training.test.entity.RestroDetails;
import com.training.test.model.RestroDetailsRequest;
import com.training.test.model.RestroOnlineRequest;
import com.training.test.service.RestroService;
import com.training.test.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restro")
@CrossOrigin(origins = "*")
public class RestaurantController {

    RestroService restroService;

    UserService userService;

    RestaurantController(RestroService restroService, UserService userService) {
        this.restroService = restroService;
        this.userService = userService;
    }


    @GetMapping("/new")
    public ResponseEntity<String> getRestaurants() {
        return new ResponseEntity<>("This is a first GET request", HttpStatus.OK);
    }


    @GetMapping("/addSaved")
    public ResponseEntity<String> processNewRestro() {
        int id = restroService.processNewRestro();
        return new ResponseEntity<>("New Restro added. ID is : " + id, HttpStatus.OK);
    }


    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteResto(@RequestBody RestroDetailsRequest restroDetailsRequest) {
        restroService.deleteRestro(restroDetailsRequest);
        return new ResponseEntity<>("Restaurant delete by ID is " + restroDetailsRequest.getId(), HttpStatus.ACCEPTED);
    }


    @GetMapping("/getDetails")
    public RestroDetails getRestro() {
        return this.restroService.getRestro();
    }


    @PutMapping("/updateDetails")
    public RestroDetails updateDetails(@RequestBody RestroDetailsRequest restroDetailsRequest) {
        return this.restroService.updateRestro(restroDetailsRequest);
    }


    @PostMapping("/addNew")
    public ResponseEntity<String> AddNewRestro(@RequestBody RestroOnlineRequest restroOnlineRequest) {

        this.restroService.AddNewRestro(restroOnlineRequest);
        return new ResponseEntity<>("Restaurant name is " + restroOnlineRequest.getName() + " and owner is " + restroOnlineRequest.getOwner() + " with contact " + restroOnlineRequest.getContact() + "\nAddress is " + restroOnlineRequest.getStreetName() + " " + restroOnlineRequest.getCity() + " " + restroOnlineRequest.getZipCode(), HttpStatus.OK);
    }


    @GetMapping("/vegRestro")
    public ResponseEntity<List<RestroDetails>> getVegRestro() {
        List<RestroDetails> vegOnlyRestro = restroService.getVegOnlyRestro();
        return new ResponseEntity<>(vegOnlyRestro, HttpStatus.OK);
    }
}