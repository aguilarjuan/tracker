package com.fiera.tracker.service.impl;

import com.fiera.tracker.model.Tracker;
import com.fiera.tracker.service.TrackerRedirectService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
@Qualifier("trackerRedirectServiceImpl")
public class TrackerRedirectServiceImpl implements TrackerRedirectService {

    @Override
    public String getUrl(Tracker TrackerModel) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(TrackerModel.getTarget() , String.class);
        return response.getBody();
    }
}
