package com.training.test.service;


import com.training.test.entity.FeedbackRestro;
import com.training.test.entity.FeedbackSite;
import com.training.test.entity.RestroDetails;
import com.training.test.model.FeedbackRestroRequest;
import com.training.test.model.FeedbackSiteRequest;
import com.training.test.repository.FeedbackRestroRepository;
import com.training.test.repository.FeedbackSiteRepository;

import com.training.test.repository.RestroDetailsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class FeedbackService {

    private FeedbackRestroRepository feedbackRestroRepository;
    private FeedbackSiteRepository feedbackSiteRepository;
    private RestroDetailsRepository restroDetailsRepository;

    public FeedbackService(FeedbackRestroRepository feedbackRestroRepository, FeedbackSiteRepository reedbackSiteRepository, RestroDetailsRepository restroDetailsRepository) {
        this.feedbackRestroRepository = feedbackRestroRepository;
        this.feedbackSiteRepository = reedbackSiteRepository;
        this.restroDetailsRepository = restroDetailsRepository;
    }


    public int addSiteFeedback(FeedbackSiteRequest request) {
        log.info("Adding site feedback for user: {}", request.getUserName());

        FeedbackSite feedbackSite = new FeedbackSite();
        feedbackSite.setUserName(request.getUserName());
        feedbackSite.setEmail(request.getEmail());
        feedbackSite.setMassage(request.getMassage());

        FeedbackSite save = feedbackSiteRepository.save(feedbackSite);
        log.info("Site feedback saved successfully with ID: {}", save.getId());

        return save.getId();
    }

    public int addRestroFeedback(FeedbackRestroRequest request) {
        log.info("Adding restaurant feedback for user: {}", request.getUserName());

        FeedbackRestro feedbackRestro = new FeedbackRestro();
        feedbackRestro.setUserName(request.getUserName());
        feedbackRestro.setEmail(request.getEmail());

        log.debug("Fetching restaurant details for ID: {}", request.getRestroId());
        RestroDetails restroDetails = restroDetailsRepository.findById(request.getRestroId())
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));
        feedbackRestro.setRestroDetails(restroDetails);
        feedbackRestro.setMessage(request.getMessage());
        feedbackRestro.setRating(request.getRating());

        log.debug("Saving restaurant feedback for user: {}", request.getUserName());
        FeedbackRestro savedFeedback = feedbackRestroRepository.save(feedbackRestro);
        log.info("Restaurant feedback saved successfully with ID: {}", savedFeedback.getId());

        return savedFeedback.getId();
    }

}
