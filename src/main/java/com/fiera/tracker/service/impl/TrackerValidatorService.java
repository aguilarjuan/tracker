package com.fiera.tracker.service.impl;

import com.fiera.tracker.model.Tracker;
import com.fiera.tracker.repository.TrackerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrackerValidatorService {

    @Autowired
    private TrackerRepository trackerRepository;

    @Value("${tracker.validation.url.pattern}")
    private String pattern;

    public boolean validateUrl(String url){
        return url.matches(pattern);
    }

    public boolean invalidateLink(String link){
        List<Tracker> trackerList = trackerRepository.findByLink(link);
        if(trackerList != null && !trackerList.isEmpty()){
            Tracker tracker = trackerList.get(0);
            tracker.setValid(false);
            trackerRepository.save(tracker);
            return true;
        } {
            return false;
        }
    }
}
