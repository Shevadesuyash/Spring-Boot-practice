package com.training.test.service;


import com.training.test.model.RestroDetailsRequest;
import org.springframework.stereotype.Service;

@Service
public class RestroService {
    public void processNewRestro(RestroDetailsRequest restroDetailsRequest) {
        String newName = restroDetailsRequest.getName();
        newName = newName.concat("test");
        restroDetailsRequest.setName(newName);
        System.out.println("  new restro name is " + restroDetailsRequest.getName());
    }

    public void delete(RestroDetailsRequest restroDetailsRequest) {

        System.out.println("Restrarant is deleted  :   " + restroDetailsRequest.getName());
        restroDetailsRequest.setName("");
    }

    public void update(RestroDetailsRequest restroDetailsRequest) {

        System.out.println("Restrarant is deleted  :   " + restroDetailsRequest.getName());
    }

}
