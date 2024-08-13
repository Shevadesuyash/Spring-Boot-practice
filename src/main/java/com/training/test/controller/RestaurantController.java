package com.training.test.controller;

import com.training.test.entity.RestroDetails;
import com.training.test.model.DeleteRequest;
import com.training.test.model.RestroOnlineRequest;
import com.training.test.model.RestroOnlineRequestUpdate;
import com.training.test.service.FeedbackService;
import com.training.test.service.RestroService;
import com.training.test.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/restro")
public class RestaurantController {

    RestroService restroService;

    UserService userService;

    FeedbackService feedbackService;

    @Value("${spring.applications.name}")
    private String Environment;

    RestaurantController(RestroService restroService, UserService userService, FeedbackService feedbackService) {
        this.restroService = restroService;
        this.userService = userService;
        this.feedbackService = feedbackService;
    }



    @Operation(summary = "Delete a restaurant", description = "Delete a restaurant based on the provided ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Restaurant deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Bad Request: Invalid ID or NOT_FOUND")
    })
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteResto(@RequestBody DeleteRequest deleteRequest) {
        if (restroService.deleteRestro(deleteRequest)) {
            return new ResponseEntity<>("Restaurant delete by ID is " + deleteRequest.getId(), HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>("Restaurant not found by ID is " + deleteRequest.getId(), HttpStatus.NOT_FOUND);
        }
    }


    @Operation(summary = "Update restaurant details", description = "Update details of a specific restaurant.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Restaurant details updated successfully"),
            @ApiResponse(responseCode = "400", description = "Bad Request: Invalid details provided"),
            @ApiResponse(responseCode = "404", description = "Not Found: Restaurant not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error: Error updating restaurant details")
    })
    @PutMapping("/updateDetails")
    public RestroDetails updateDetails(@RequestBody RestroOnlineRequestUpdate restroDetailsRequest) {
        RestroDetails updatedDetails = restroService.updateRestro(restroDetailsRequest);
        log.info("Updated restaurant details: {}", updatedDetails);
        return updatedDetails;
    }

    @Operation(summary = "Add a new restaurant", description = "Add a new restaurant with provided details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Restaurant added successfully"),
            @ApiResponse(responseCode = "400", description = "Bad Request: Invalid details provided"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error: Error adding new restaurant")
    })
    @PostMapping("/addNew")
    public ResponseEntity<String> AddNewRestro(@RequestBody RestroOnlineRequest restroOnlineRequest) {

        this.restroService.addNewRestro(restroOnlineRequest);
        log.info("New restaurant added: {}", restroOnlineRequest);
        return new ResponseEntity<>("Restaurant name is " + restroOnlineRequest.getName()
                + " and owner is " + restroOnlineRequest.getOwner() + " with contact "
                + restroOnlineRequest.getContact() + "  Email:  "+restroOnlineRequest.getEmail()+"\nAddress is " + restroOnlineRequest.getStreetName()
                + " " + restroOnlineRequest.getCity() + " " + restroOnlineRequest.getZipCode(), HttpStatus.OK);
    }



    @Operation(summary = "Get all restaurant details", description = "Retrieve all restaurant entries stored in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Restaurant details returned"),
            @ApiResponse(responseCode = "400", description = "Error while retrieving the restaurant."),
            @ApiResponse(responseCode = "500", description = "Error with the server. Contact backend team")
    })
    @GetMapping("/allRestro")
    public ResponseEntity<List<RestroDetails>> getallRestro() {
        List<RestroDetails> allRestro = restroService.allRestro();
        log.info("getAllRestro");
        return new ResponseEntity<>(allRestro, HttpStatus.OK);

    }
}