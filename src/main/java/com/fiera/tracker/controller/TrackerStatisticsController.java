package com.fiera.tracker.controller;

import com.fiera.tracker.message.TrackerStatisticsDTO;
import com.fiera.tracker.model.Tracker;
import com.fiera.tracker.service.impl.TrackerStatisticsServiceImpl;
import com.fiera.tracker.service.impl.TrackerValidatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static com.fiera.tracker.constans.Messages.LINK_NOT_FOUND;

@RestController
@RequestMapping("/tracker/statistics")
public class TrackerStatisticsController {

    @Autowired
    private TrackerValidatorService trackerValidatorService;

    @Autowired
    private TrackerStatisticsServiceImpl trackerStatisticsService;

    @GetMapping( value = "/redirect" , consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    ResponseEntity<?> getStatisticsRedirect(String link){
        Optional<Tracker> trackerOptional = trackerValidatorService.getTrackerModel(link);
        if (trackerOptional.isPresent()){
            TrackerStatisticsDTO response = trackerStatisticsService.getRedirect(trackerOptional.get());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(LINK_NOT_FOUND, HttpStatus.NOT_FOUND);
        }
    }
}
