package com.fiera.tracker.controller;

import com.fiera.tracker.model.Tracker;
import com.fiera.tracker.service.TrackerRedirectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import java.util.Optional;

import static com.fiera.tracker.constans.Messages.FORMAT_BAD_URL;

@RestController
@RequestMapping("/l/")
public class RedirectController {

    @Autowired
    @Qualifier("trackerRedirectProxy")
    private TrackerRedirectService trackerRedirectService;

    final static String REGEX = "/l/";

    final static Logger log = LoggerFactory.getLogger(RedirectController.class);

    @GetMapping( value = "*")
    ResponseEntity<?> redirectTracker(HttpServletRequest httpServletRequest){
        log.info("estoy en el controlador de RedirectController: " + httpServletRequest.getServletPath());
        String[] arrOfStr = httpServletRequest.getServletPath().split(REGEX);
        Optional<String> urlResponse = trackerRedirectService.getUrl(arrOfStr[1]);
        if (urlResponse.isPresent()){
            return new ResponseEntity<>(urlResponse.get(), HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(FORMAT_BAD_URL, HttpStatus.NOT_FOUND);
        }
    }
}
