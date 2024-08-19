package com.training.test.repository;

import com.training.test.entity.RestroDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface RestroDetailsRepository extends JpaRepository<RestroDetails, Integer> {

    @Query(value = "select * from restro_details where restro_type = 'veg'", nativeQuery = true)
    List<RestroDetails> getVegOnlyRestro();

    @Query(value = "select * from restro_details where restro_name = ?", nativeQuery = true)
    List<RestroDetails> findAllByRestroName(String restroName);
    List<RestroDetails> findAllByOwnerName(String ownerName);
    List<RestroDetails> findAllByContact(Long contact);
    Optional<RestroDetails> findById(int id);
    List<RestroDetails> findAllByRestroType(String restro_type);


}
