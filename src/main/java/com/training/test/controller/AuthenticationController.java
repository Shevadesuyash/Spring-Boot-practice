package com.training.test.controller;

import com.training.test.model.LoginRequest;
import com.training.test.model.UserRegistrationRequest;
import com.training.test.service.RestroService;
import com.training.test.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class AuthenticationController {

    RestroService restroService;

    UserService userService;

    AuthenticationController(RestroService restroService, UserService userService) {
        this.restroService = restroService;
        this.userService = userService;
    }


    @PostMapping("/register")
    public ResponseEntity<String> AddNewUser(@RequestBody UserRegistrationRequest userRegistrationRequest) {

        this.userService.AddNewUser(userRegistrationRequest);
        return new ResponseEntity<>("User name is " + userRegistrationRequest.getUserName(), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginRequest loginRequest) {
        boolean isAuthenticated = userService.authenticateUser(loginRequest);
        if (isAuthenticated) {
            return new ResponseEntity<>("Login successful!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid username or password.", HttpStatus.UNAUTHORIZED);
        }
    }
}
