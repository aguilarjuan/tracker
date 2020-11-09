package com.fiera.tracker.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TrackerValidatorService {

    @Value("${tracker.validation.url.pattern}")
    private String pattern;

    public boolean validateUrl(String url){
        return url.matches(pattern);
    }
}
