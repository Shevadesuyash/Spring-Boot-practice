package com.training.test.service;

import com.training.test.entity.RestaurantAddressDetails;
import com.training.test.entity.RestroDetails;
import com.training.test.model.DeleteRequest;
import com.training.test.model.RestroDetailsRequest;
import com.training.test.model.RestroOnlineRequest;
import com.training.test.repository.RestroDetailsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Slf4j
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
        log.info("Processing Saved restaurant");
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
        log.info("New restaurant Blue Nile added with ID: {}", restaurantDetails.getId());
        return restaurantDetails.getId();
    }


    public List<List> getRestro(RestroDetailsRequest request) {
        List<List> restaurantDetails = new ArrayList<>();

        if (request.getId() != null && !request.getId().equals("")) {
            restaurantDetails.add(restaurantDetailsRespository.findById(Integer.parseInt(request.getId())).stream().toList());
            log.info("Found restaurant with ID: {}", request.getId());
        }
        if (request.getName() != null && !request.getName().equals("")) {
            restaurantDetails.add(restaurantDetailsRespository.findAllByRestroName(request.getName()));
            log.info("Found restaurant with name: {}", request.getName());
        }
        if (request.getOwner() == null && !request.getOwner().equals("")) {
            restaurantDetails.add(restaurantDetailsRespository.findAllByOwnerName(request.getOwner()));
            log.info("Found restaurant with owner: {}", request.getOwner());
        }
        if (request.getContact() != null && !request.getContact().equals("")) {
            restaurantDetails.add(restaurantDetailsRespository.findAllByContact(Long.parseLong(request.getContact())));
            log.info("Found restaurant with contact: {}", request.getContact());
        }
        if (request.getType() != null && !request.getType().equals("")) {
            restaurantDetails.add(restaurantDetailsRespository.findAllByRestroType(request.getType()));
            log.info("Found restaurant with type: {}", request.getType());
        }
        if (restaurantDetails == null || restaurantDetails.isEmpty()) {
            return null;
        } else {
            log.info("Found restaurant {}", restaurantDetails.stream().count());
            return restaurantDetails;
        }
    }


    public RestroDetails updateRestro(RestroDetailsRequest restroDetailsRequest) {
        Optional<RestroDetails> restaurantDetails = null;

        restaurantDetails = restaurantDetailsRespository.findById(12);
        if (restaurantDetails.isPresent()) {
            RestroDetails restroDetails = restaurantDetails.get();
            restroDetails.setRestroName(restroDetailsRequest.getName());
            restaurantDetailsRespository.save(restroDetails);
        } else {
            log.warn("Restaurant details not found for ID: {}", restroDetailsRequest.getId());
            return null;
        }

        return restaurantDetails.get();
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

        restaurantDetailsRespository.save(restroDetails);
        log.info("New Restro added  : {}", restroOnlineRequest.getName());
    }


    public List<RestroDetails> getVegOnlyRestro() {
        return restaurantDetailsRespository.getVegOnlyRestro();
    }

    public List<RestroDetails> allRestro() {
        return restaurantDetailsRespository.findAll();
    }
}




