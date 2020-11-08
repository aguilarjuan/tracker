package com.fiera.tracker.service;

import com.fiera.tracker.message.TrackerDTO;
import com.fiera.tracker.model.Tracker;
import com.fiera.tracker.repository.TrackerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrackerService {

@Autowired
private TrackerValidatorService trackerValidatorService;

@Autowired
private TrackerRepository trackerRepository;

public TrackerDTO createTracker(String url) {
    return null;
}

private TrackerDTO buildTrackerDto(Tracker trackerModel){
    return null;
}

}
