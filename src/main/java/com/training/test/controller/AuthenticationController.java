package com.training.test.controller;

import com.training.test.model.LoginRequest;
import com.training.test.model.UserRegistrationRequest;
import com.training.test.service.RestroService;
import com.training.test.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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


    @Operation(summary = "Register a new user", description = "Registers a new user with unique email, username, and phone.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User registered successfully"),
            @ApiResponse(responseCode = "409", description = "Conflict: Email, username, or phone already in use"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error: An unexpected error occurred")
    })
    @PostMapping("/register")
    public ResponseEntity<String> AddNewUser(@RequestBody UserRegistrationRequest userRegistrationRequest) {

        String s = this.userService.addNewUser(userRegistrationRequest);
        if (s.charAt(0) == 'U') {
            return new ResponseEntity<>(s, HttpStatus.OK);
        } else if (s.charAt(0) == 'e') {
            return new ResponseEntity<>(s, HttpStatus.CONFLICT);
        } else {
            return new ResponseEntity<>("An error occurred during registration", HttpStatus.BAD_REQUEST);
        }


    }


    @Operation(summary = "User login", description = "Authenticates a user based on username and password.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login successful"),
            @ApiResponse(responseCode = "401", description = "Unauthorized: Invalid username or password")
    })
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
