package com.fiera.tracker.controller;

import com.fiera.tracker.mapper.TrackerMapper;
import com.fiera.tracker.message.TrackerDTO;
import com.fiera.tracker.model.Tracker;
import com.fiera.tracker.service.impl.TrackerCreateServiceImpl;
import com.fiera.tracker.service.impl.TrackerValidatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.fiera.tracker.constans.Messages.FORMAT_BAD_URL;

@RestController
@RequestMapping("/tracker")
public class TrackerController {

    @Autowired
    @Qualifier("trackerCreateProxy")
    private TrackerCreateServiceImpl trackerCreateProxy;

    @Autowired
    private TrackerValidatorService trackerValidatorService;

    @Autowired
    private TrackerMapper trackerMapper;

    final static Logger log = LoggerFactory.getLogger(TrackerController.class);

    @PostMapping( value = "/create", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE )
    ResponseEntity<?> createTracker(String url){
        if(trackerValidatorService.validateUrl(url)){
            Tracker response = trackerCreateProxy.createTracker(url);
            return new ResponseEntity<>(trackerMapper.toDto(response), HttpStatus.CREATED);
        } else {
          return new ResponseEntity<>(FORMAT_BAD_URL, HttpStatus.BAD_REQUEST);
        }
    }
}
