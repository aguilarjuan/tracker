package com.fiera.tracker.controller;

import com.fiera.tracker.model.Tracker;
import com.fiera.tracker.service.TrackerRedirectService;
import com.fiera.tracker.service.impl.TrackerValidatorServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import java.util.Optional;

import static com.fiera.tracker.constans.Messages.*;

@RestController
@RequestMapping("/l")
public class TrackerRedirectController {

    @Autowired
    @Qualifier("trackerRedirectProxy")
    private TrackerRedirectService trackerRedirectService;

    @Autowired
    private TrackerValidatorServiceImpl trackerValidatorServiceImpl;

    @Value("${tracker.urlLink}")
    private String urlLink;

    final static String REGEX = "/l/";

    final static Logger log = LoggerFactory.getLogger(TrackerRedirectController.class);

    @GetMapping( value = "/*")
    ResponseEntity<?> redirectTracker(HttpServletRequest httpServletRequest, @RequestParam String password){
        String[] arrOfStr = httpServletRequest.getServletPath().split(REGEX);
        Optional<Tracker> trackerOptional = trackerValidatorServiceImpl.getTrackerModel(urlLink + arrOfStr[1]);
        if(trackerOptional.isPresent()){
            if(trackerValidatorServiceImpl.allowAccess(trackerOptional.get(),password)){
                String urlTarget = trackerRedirectService.getUrl(trackerOptional.get());
                return new ResponseEntity<>(urlTarget, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(AUTHENTICATION_FAILED, HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(LINK_NOT_FOUND, HttpStatus.NOT_FOUND);
        }
    }
}
