package com.fiera.tracker.controller;

import com.fiera.tracker.message.TrackerDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tracker")
public class TrackerController {

    final static Logger log = LoggerFactory.getLogger(TrackerController.class);

    @PostMapping("/create")
    ResponseEntity<TrackerDTO> createTracker(){
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }
}
