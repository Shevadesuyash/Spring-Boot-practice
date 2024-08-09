package com.training.test.controller;

import com.training.test.model.FeedbackRestroRequest;
import com.training.test.model.FeedbackSiteRequest;
import com.training.test.service.FeedbackService;
import com.training.test.service.RestroService;
import com.training.test.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/feedback")
public class FeedbackController {
    private final RestroService restroService;
    private final UserService userService;
    private final FeedbackService feedbackService;

    public FeedbackController(RestroService restroService, UserService userService, FeedbackService feedbackService) {
        this.restroService = restroService;
        this.userService = userService;
        this.feedbackService = feedbackService;
    }

    @Operation(summary = "Submit site feedback", description = "Submit feedback for the site.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Feedback received and saved"),
            @ApiResponse(responseCode = "400", description = "Feedback not received")
    })
    @PostMapping("/site_feedback")
    public ResponseEntity<String> getSiteFeedback(@RequestBody FeedbackSiteRequest request) {
        try {
            int feedback_id = feedbackService.addSiteFeedback(request);
            return ResponseEntity.ok("Feedback received and saved with id: " + feedback_id);
        } catch (Exception e) {
            return new ResponseEntity<>("Feedback not received", HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Submit restaurant feedback", description = "Submit feedback for the restaurant.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Feedback received and saved"),
            @ApiResponse(responseCode = "400", description = "Feedback not received")
    })
    @PostMapping("/restro_feedback")
    public ResponseEntity<String> getRestroFeedback(@RequestBody FeedbackRestroRequest request) {
        try {
            int restro_id = feedbackService.addRestroFeedback(request);
            return ResponseEntity.ok("Feedback received and saved with id: " + restro_id);
        } catch (Exception e) {
            return new ResponseEntity<>("Feedback not received", HttpStatus.BAD_REQUEST);
        }
    }
}
