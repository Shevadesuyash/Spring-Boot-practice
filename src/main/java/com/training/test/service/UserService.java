package com.training.test.service;

import com.training.test.entity.UserDetails;
import com.training.test.model.LoginRequest;
import com.training.test.model.UserRegistrationRequest;
import com.training.test.repository.UserDetailsRepository;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserService {

    private UserDetailsRepository userDetailsRepository;

    public UserService(UserDetailsRepository userDetailsRepository) {
        this.userDetailsRepository = userDetailsRepository;
    }


    public String addNewUser(UserRegistrationRequest userRegistrationRequest) {


        UserDetails userDetails = new UserDetails();
        userDetails.setFirstName(userRegistrationRequest.getFirstName());
        userDetails.setLastName(userRegistrationRequest.getLastName());
        userDetails.setEmail(userRegistrationRequest.getEmail());
        userDetails.setPhone(Long.parseLong(userRegistrationRequest.getPhone()));
        userDetails.setUsername(userRegistrationRequest.getUserName());
        userDetails.setPassword(userRegistrationRequest.getPassword());
        try {
            userDetailsRepository.save(userDetails);
            log.info("New User added  : {}", userRegistrationRequest.getUserName());
            return ("User name is " + userRegistrationRequest.getUserName());
        } catch (RuntimeException e) {
            if (e.toString().contains("Key (email)")) {
                log.warn(" Email Already in use");
                return "e -Email already in use";
            } else if (e.toString().contains("Key (username)")) {
                log.warn("Username Already in use");
                return "e -Username already in use";
            } else if (e.toString().contains(" Key (phone)")) {
                log.warn("Mobile number is already registered");
                return "e -Mobile number is already registered";
            } else {
                return "e -An error occurred during registration";
            }

        }

    }


    public boolean authenticateUser(LoginRequest loginRequest) {
        Optional<UserDetails> userDetails = userDetailsRepository.findByUsername(loginRequest.getUserName());
        return userDetails.map(details -> details.getPassword().equals(loginRequest.getPassword())).orElse(false);
    }

    public List<UserDetails> getUserDetails() {
        return userDetailsRepository.findAll();
    }
}
