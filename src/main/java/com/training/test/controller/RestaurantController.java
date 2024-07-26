package com.training.test.controller;

import com.training.test.entity.RestroDetails;
import com.training.test.model.RestroDetailsRequest;
import com.training.test.model.RestroOnlineRequest;

import com.training.test.service.RestroService;
import com.training.test.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restro")
@CrossOrigin(origins="*")
public class RestaurantController {


    //    @Autowired
    RestroService restroService;

    //    @Autowired
    UserService userService;

    RestaurantController(RestroService restroService, UserService userService) {
        this.restroService = restroService;
        this.userService = userService;
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

//    @PostMapping("/delete")
//    public ResponseEntity<String> deleteResto(@RequestBody RestroDetailsRequest restroDetailsRequest) {
//        return new ResponseEntity<>("Restaurant delete by name is " + restroDetailsRequest.getName() + " AND user is " + restroDetailsRequest.getUsername(), HttpStatus.ACCEPTED);
//    }

//    @PostMapping("/update")
//    public ResponseEntity<String> update(@RequestBody RestroDetailsRequest restroDetailsRequest) {
//        return new ResponseEntity<>("Restaurant updated name is " + restroDetailsRequest.getName() + " AND  new name is " + restroDetailsRequest.getUsername(), HttpStatus.ACCEPTED);
//    }


    // 24 july
    //----------------------------------------------------------------

    @GetMapping("/getDetails")
    public RestroDetails getRestro() {
        return this.restroService.getRestro();

    }

//    @PostMapping("/updateDetails")
//    public RestroDetails updateDetails() {
//        return this.restroService.updateRestro();
//
//
//    }

    //----------------------------------------------------------------
    //25 july
    @PostMapping("/addNew")
    public ResponseEntity<String> AddNewRestro(@RequestBody RestroOnlineRequest restroOnlineRequest) {

        this.restroService.AddNewRestro(restroOnlineRequest);
        return new ResponseEntity<>("Restaurant name is " + restroOnlineRequest.getName() +
                " and owner is " + restroOnlineRequest.getOwner() + " with contact "
                + restroOnlineRequest.getContact() + "\nAddress is "
                + restroOnlineRequest.getStreetName() + " " + restroOnlineRequest.getCity()
                + " " + restroOnlineRequest.getZipCode(), HttpStatus.OK);
    }


}