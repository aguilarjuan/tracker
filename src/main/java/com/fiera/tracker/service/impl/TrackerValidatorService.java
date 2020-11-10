package com.fiera.tracker.service.impl;

import com.fiera.tracker.repository.TrackerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class TrackerValidatorService {

    @Autowired
    private TrackerRepository trackerRepository;

    @Value("${tracker.validation.url.pattern}")
    private String pattern;

    public boolean validateUrl(String url){
        return url.matches(pattern);
    }

}
