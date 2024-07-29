package com.training.test.repository;

import com.training.test.entity.RestroDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RestroDetailsRepository extends JpaRepository<RestroDetails, Integer> {

    @Query(value = "select * from restro_details where restro_type = 'veg'", nativeQuery = true)
    List<RestroDetails> getVegOnlyRestro();
}
