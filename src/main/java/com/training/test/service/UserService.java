package com.training.test.service;

import com.training.test.entity.UserDetails;
import com.training.test.model.LoginRequest;
import com.training.test.model.UserRegistrationRequest;
import com.training.test.repository.UserDetailsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class UserService {

    private UserDetailsRepository userDetailsRepository;

    public UserService(UserDetailsRepository userDetailsRepository) {
        this.userDetailsRepository = userDetailsRepository;
    }


    public void AddNewUser(UserRegistrationRequest userRegistrationRequest) {
        UserDetails userDetails = new UserDetails();
        userDetails.setFirstName(userRegistrationRequest.getFirstName());
        userDetails.setLastName(userRegistrationRequest.getLastName());
        userDetails.setEmail(userRegistrationRequest.getEmail());
        userDetails.setPhone(Long.parseLong(userRegistrationRequest.getPhone()));
        userDetails.setUsername(userRegistrationRequest.getUserName());
        userDetails.setPassword(userRegistrationRequest.getPassword());

        userDetailsRepository.save(userDetails);
        log.info("New User added  : {}",userRegistrationRequest.getUserName());

    }


    public boolean authenticateUser(LoginRequest loginRequest) {
        Optional<UserDetails> userDetails = userDetailsRepository.findByUsername(loginRequest.getUserName());
        return userDetails.map(details -> details.getPassword().equals(loginRequest.getPassword())).orElse(false);
    }
}
