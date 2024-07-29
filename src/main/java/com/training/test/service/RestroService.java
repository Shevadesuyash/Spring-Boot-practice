package com.training.test.service;

import com.training.test.entity.RestaurantAddressDetails;
import com.training.test.entity.RestroDetails;
import com.training.test.model.RestroDetailsRequest;
import com.training.test.model.RestroOnlineRequest;
import com.training.test.repository.RestroDetailsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class RestroService {

    private UserService userService;

    private RestroDetailsRepository restaurantDetailsRespository;

    public RestroService(RestroDetailsRepository restroDetailsRepository, UserService userService) {
        this.restaurantDetailsRespository = restroDetailsRepository;
        this.userService = userService;
    }


    public int processNewRestro() {

        RestaurantAddressDetails addressDetails = new RestaurantAddressDetails();
        addressDetails.setStreetName("Koregaon Park");
        addressDetails.setCity("Pune");
        addressDetails.setPinCode(411030);

        RestroDetails restaurantDetails = new RestroDetails();
        restaurantDetails.setRestroName("Blue Nile");
        restaurantDetails.setOwnerName("Suyash Shevade");
        restaurantDetails.setAddressDetails(addressDetails);
        restaurantDetails.setRestroType("non-veg");
        restaurantDetails.setContact(1234567890L);

        restaurantDetailsRespository.save(restaurantDetails);

        System.out.print("New Restro name Blue Nile added at " + restaurantDetails.getId());
        return restaurantDetails.getId();
    }


    public RestroDetails getRestro() {
        List<RestroDetails> restaurantDetails = null;

        restaurantDetails = restaurantDetailsRespository.findAll();

        return restaurantDetails.getFirst();
    }


    public RestroDetails updateRestro(RestroDetailsRequest restroDetailsRequest) {
        Optional<RestroDetails> restaurantDetails = null;

        restaurantDetails = restaurantDetailsRespository.findById(12);
        if (restaurantDetails.isPresent()) {
            RestroDetails restroDetails = restaurantDetails.get();
            restroDetails.setRestroName(restroDetailsRequest.getName());
            restaurantDetailsRespository.save(restroDetails);
        }

        return restaurantDetails.get();
    }


    public void deleteRestro(RestroDetailsRequest restroDetailsRequest) {
        Optional<RestroDetails> restaurantDetails = null;

        restaurantDetails = restaurantDetailsRespository.findById(restroDetailsRequest.getId());
        if (restaurantDetails.isPresent()) {
            RestroDetails restroDetails = restaurantDetails.get();
            //restroDetails.delete();
            // restaurantDetailsRespository.save(restroDetails);
        }
        System.out.println("Restrarant is deleted  :   " + restroDetailsRequest.getId());
    }


    public void AddNewRestro(RestroOnlineRequest restroOnlineRequest) {

        RestaurantAddressDetails addressDetails = new RestaurantAddressDetails();
        addressDetails.setCity(restroOnlineRequest.getCity());
        addressDetails.setStreetName(restroOnlineRequest.getStreetName());
        addressDetails.setPinCode(Integer.parseInt(restroOnlineRequest.getZipCode()));

        RestroDetails restroDetails = new RestroDetails();
        restroDetails.setRestroName(restroOnlineRequest.getName());
        restroDetails.setOwnerName(restroOnlineRequest.getOwner());
        restroDetails.setAddressDetails(addressDetails);
        restroDetails.setRestroType(restroOnlineRequest.getType());
        restroDetails.setContact(Long.parseLong(restroOnlineRequest.getContact()));

        restaurantDetailsRespository.save(restroDetails);
        System.out.println("New Restro added  :   " + restroOnlineRequest.getName());
    }


    public List<RestroDetails> getVegOnlyRestro() {
        return restaurantDetailsRespository.getVegOnlyRestro();
    }
}




