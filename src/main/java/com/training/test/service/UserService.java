package com.training.test.service;

import com.training.test.entity.UserDetails;
import com.training.test.model.UserRegistrationRequest;
import com.training.test.repository.UserDetailsRepository;
import org.springframework.stereotype.Service;

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
        System.out.println("New User added  :   " + userRegistrationRequest.getUserName());

    }
}
