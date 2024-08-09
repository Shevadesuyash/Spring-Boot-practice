package com.training.test.controller;

import com.training.test.entity.UserDetails;
import com.training.test.model.ForgotPassword;
import com.training.test.model.LoginRequest;
import com.training.test.model.UserDetailResponse;
import com.training.test.model.UserRegistrationRequest;
import com.training.test.service.FeedbackService;
import com.training.test.service.RestroService;
import com.training.test.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
public class AuthenticationController {

    RestroService restroService;

    UserService userService;

    FeedbackService feedbackService;

    AuthenticationController(RestroService restroService, UserService userService, FeedbackService feedbackService) {
        this.restroService = restroService;
        this.userService = userService;
        this.feedbackService = feedbackService;
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

    @Operation(summary = "Get user details", description = "Fetches user details including ID, username, and email.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User details fetched successfully"),
            @ApiResponse(responseCode = "204", description = "No user details found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error: An unexpected error occurred")
    })

    @GetMapping("/details")
    public ResponseEntity<List<UserDetailResponse>> getUserDetails() {
        List<UserDetailResponse> userDetails = userService.getUserDetails();
        if (!userDetails.isEmpty()) {
            log.info("User details fetched successfully");
        }
        return new ResponseEntity<>(userDetails, HttpStatus.OK);
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<List<String>> getForgotPassword(@RequestBody ForgotPassword password) {
        Optional<UserDetails> userFound = userService.isUser(password.getUserName());

        if (userFound.isPresent()) {
            UserDetails userDetails = userFound.get();
            if (userDetails.getEmail().equals(password.getEmail()) && userDetails.getPhone().equals(password.getPhone())) {
                return new ResponseEntity<>(List.of("Password reset link sent to your registered email."), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(List.of("Details Does not match. Try again. "), HttpStatus.UNAUTHORIZED);
            }
        } else {
            return new ResponseEntity<>(List.of("No such user found."), HttpStatus.NOT_FOUND);
        }
    }


}
