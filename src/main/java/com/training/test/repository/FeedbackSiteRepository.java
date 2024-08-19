package com.training.test.repository;

import com.training.test.entity.FeedbackSite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackSiteRepository extends JpaRepository<FeedbackSite , Integer> {


}
