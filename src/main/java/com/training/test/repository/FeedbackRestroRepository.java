package com.training.test.repository;

import com.training.test.entity.FeedbackRestro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRestroRepository extends JpaRepository<FeedbackRestro, Integer> {


}
