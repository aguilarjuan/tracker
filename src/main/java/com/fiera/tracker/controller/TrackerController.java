package com.fiera.tracker.controller;

import com.fiera.tracker.mapper.TrackerMapper;
import com.fiera.tracker.model.Tracker;
import com.fiera.tracker.service.TrackerCreateService;
import com.fiera.tracker.service.impl.TrackerValidatorServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.fiera.tracker.constans.Messages.*;

@RestController
@RequestMapping("/tracker")
public class TrackerController {

    @Autowired
    @Qualifier("trackerCreateProxy")
    private TrackerCreateService trackerCreateProxy;

    @Autowired
    private TrackerValidatorServiceImpl trackerValidatorServiceImpl;

    @Autowired
    private TrackerMapper trackerMapper;

    final static Logger log = LoggerFactory.getLogger(TrackerController.class);

    @PostMapping( value = "/create", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    ResponseEntity<?> createTracker(String url, String password, String expirationDate){
        if(trackerValidatorServiceImpl.validateUrl(url)){
              if(password != null && !password.isEmpty()){
                  Tracker response = trackerCreateProxy.createTracker(url,password,expirationDate);
                  return new ResponseEntity<>(trackerMapper.toDto(response), HttpStatus.CREATED);
              } else {
                  return new ResponseEntity<>(PASSWORD_AUTHENTICATION_REQUIRED, HttpStatus.FORBIDDEN);
              }
        } else {
          return new ResponseEntity<>(FORMAT_BAD_URL, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping( value = "/invalidate/link", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    ResponseEntity<?> invalidateLink(String link){
        if(trackerValidatorServiceImpl.invalidateLink(link)){
            return new ResponseEntity<>(INVALID_LINK_ACCEPTED, HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(LINK_NOT_FOUND, HttpStatus.NOT_FOUND);
        }
    }
}
