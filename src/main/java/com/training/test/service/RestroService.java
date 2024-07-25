package com.training.test.service;


import com.training.test.entity.RestaurantAddressDetails;
import com.training.test.entity.RestroDetails;
import com.training.test.model.RestroDetailsRequest;

import com.training.test.repository.RestroDetailsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class RestroService {


    private LoginService loginService;

    private RestroDetailsRepository restaurantDetailsRespository;

    public RestroService(RestroDetailsRepository restroDetailsRepository, RestaurantAddressDetails addressDetailsRepository) {
        this.restaurantDetailsRespository = restaurantDetailsRespository;
        this.loginService = loginService;
    }

    public void processNewRestro(RestroDetailsRequest restroDetailsRequest) {

        RestaurantAddressDetails addressDetails = new RestaurantAddressDetails();
        addressDetails.setStreetName("Koregaon Park");
        addressDetails.setCity("Pune");
        addressDetails.setPinCode(411030);

        RestroDetails restaurantDetails = new RestroDetails();
        restaurantDetails.setName("Blue Nile");
        restaurantDetails.setOwnerName("Suyash Shevade");
        restaurantDetails.setAddressDetails(addressDetails);
        restaurantDetails.setRestroType("non-veg");
        restaurantDetails.setContact(1234567890L);

        restaurantDetailsRespository.save(restaurantDetails);


        System.out.print("New Restro name "+restroDetailsRequest.getName());
    }

    public RestroDetails getRestro(){
        List<RestroDetails> restaurantDetails = null;

        restaurantDetails = restaurantDetailsRespository.findAll();

        return restaurantDetails.getFirst();
    }

    public RestroDetails updateRestro() {
        Optional<RestroDetails> restaurantDetails = null;

        restaurantDetails = restaurantDetailsRespository.findById(0);
//        restaurantDetails.isPresent(details -> {
//            details.setname("Sarthak");
//            restaurantDetailsRespository.save(details);
//        });

        return restaurantDetails.get();
    }


    public void delete(RestroDetailsRequest restroDetailsRequest) {

        System.out.println("Restrarant is deleted  :   " + restroDetailsRequest.getName());
        restroDetailsRequest.setName("");
    }

    public void update(RestroDetailsRequest restroDetailsRequest) {

        System.out.println("Restrarant is deleted  :   " + restroDetailsRequest.getName());
    }
}




