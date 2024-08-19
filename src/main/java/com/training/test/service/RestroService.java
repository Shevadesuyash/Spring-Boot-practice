package com.training.test.service;

import com.training.test.entity.RestaurantAddressDetails;
import com.training.test.entity.RestroDetails;
import com.training.test.model.DeleteRequest;
import com.training.test.model.RestroOnlineRequest;
import com.training.test.model.RestroOnlineRequestUpdate;
import com.training.test.repository.RestroDetailsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Slf4j
@Service
public class RestroService {

    private UserService userService;

    private FeedbackService feedbackService;

    private RestroDetailsRepository restaurantDetailsRespository;


    public RestroService(RestroDetailsRepository restroDetailsRepository, UserService userService, FeedbackService feedbackService, RestroDetailsRepository restaurantDetailsRespository) {
        this.restaurantDetailsRespository = restroDetailsRepository;
        this.userService = userService;
        this.feedbackService = feedbackService;
    }


    public RestroDetails updateRestro(RestroOnlineRequestUpdate restroDetailsRequest) {
        log.info("Attempting to update restaurant details for ID: {}", restroDetailsRequest.getId());

        Optional<RestroDetails> restaurantDetails = restaurantDetailsRespository.findById(restroDetailsRequest.getId());

        if (restaurantDetails.isPresent()) {
            log.info("Restaurant found for ID: {}", restroDetailsRequest.getId());

            RestroDetails restroDetails = restaurantDetails.get();

            log.debug("Updating restaurant name, owner, type, contact, and email");
            restroDetails.setRestroName(restroDetailsRequest.getName());
            restroDetails.setOwnerName(restroDetailsRequest.getOwner());
            restroDetails.setRestroType(restroDetailsRequest.getType());
            restroDetails.setContact(Long.parseLong(restroDetailsRequest.getContact()));
            restroDetails.setEmail(restroDetailsRequest.getEmail());

            RestaurantAddressDetails addressDetails = restroDetails.getAddressDetails();
            if (addressDetails == null) {
                log.debug("No existing address found. Creating a new address entry.");
                addressDetails = new RestaurantAddressDetails();
            }
            log.debug("Updating restaurant address: city={}, streetName={}, pinCode={}",
                    restroDetailsRequest.getCity(),
                    restroDetailsRequest.getStreetName(),
                    restroDetailsRequest.getZipCode());
            addressDetails.setCity(restroDetailsRequest.getCity());
            addressDetails.setStreetName(restroDetailsRequest.getStreetName());
            addressDetails.setPinCode(Integer.parseInt(restroDetailsRequest.getZipCode()));

            restroDetails.setAddressDetails(addressDetails);

            restaurantDetailsRespository.save(restroDetails);
            log.info("Successfully updated restaurant details for ID: {}", restroDetailsRequest.getId());
            return restroDetails;
        } else {
            log.warn("Restaurant details not found for ID: {}", restroDetailsRequest.getId());
            return null;
        }
    }


    public boolean deleteRestro(DeleteRequest deleteRequest) {
        Optional<RestroDetails> restaurantDetails = null;
        restaurantDetails = restaurantDetailsRespository.findById(deleteRequest.getId());
        if (restaurantDetails.isPresent()) {
            restaurantDetailsRespository.deleteById(deleteRequest.getId());
            log.info("Restrarant is deleted  :   {}", deleteRequest.getId());
            return true;
        } else {
            log.info("No Restrarant by id  :   {}", deleteRequest.getId());
            return false;
        }
    }


    public void addNewRestro(RestroOnlineRequest restroOnlineRequest) {

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
        restroDetails.setEmail(restroOnlineRequest.getEmail());

        restaurantDetailsRespository.save(restroDetails);
        log.info("New Restro added  : {}", restroOnlineRequest.getName());
    }


    public List<RestroDetails> allRestro() {
        return restaurantDetailsRespository.findAll(Sort.by(Sort.Order.asc("id")));
    }
}




