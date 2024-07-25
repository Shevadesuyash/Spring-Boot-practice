package com.training.test.repository;

import com.training.test.entity.RestroDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestroDetailsRepository extends JpaRepository<RestroDetails, Integer> {


}
